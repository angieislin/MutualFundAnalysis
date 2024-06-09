package com.anonymity.scraping.mutualfundanalysis.task;

import com.anonymity.scraping.mutualfundanalysis.dao.AssetValueDao;
import com.anonymity.scraping.mutualfundanalysis.entity.*;
import com.anonymity.scraping.mutualfundanalysis.respository.DataConfigRepository;
import com.anonymity.scraping.mutualfundanalysis.respository.FundRepository;
import com.anonymity.scraping.mutualfundanalysis.respository.LatestFundDetailRepository;
import com.anonymity.scraping.mutualfundanalysis.utils.ConstantUtils;
import com.anonymity.scraping.mutualfundanalysis.utils.DateUtils;
import com.anonymity.scraping.mutualfundanalysis.utils.ScrapyUtils;
import jakarta.transaction.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private DataConfigRepository dataConfigRepository;
    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private LatestFundDetailRepository latestFundDetailRepository;
    @Autowired
    private AssetValueDao assetValueDao;


    @Transactional
    public void dropPreviousData() {
        fundRepository.deleteAllInBatch();
    }
    // only get Top 6 funds
    private List<DataConfig>  getAllDataConfig(WebDriver driver) throws InterruptedException{
        logger.info("getAllDataConfig...");
        driver.get(ConstantUtils.mainUrl);
        Thread.sleep(5000);
        Document document = Jsoup.parse(driver.getPageSource());
        Elements rows = document.select("#tableEachDetail .mainTb tbody tr");
        List<Element> filteredRows = rows.subList(0, Math.min(6, rows.size()));
        List<DataConfig> configs = new ArrayList<>();
        for (Element row : filteredRows) {
            Elements cols = row.select("td");
            String code = cols.get(0).text();
            String name = cols.get(1).text();
            String resourceMainUrl = cols.get(1).select("a").attr("href");
            FundInfo fund = new FundInfo(code, name, null);
            DataConfig dc =  new DataConfig(fund, resourceMainUrl);
            configs.add(dc);
        }
        return configs;
    }

    private void setHistoryNetAssetValue(WebDriver driver, FundInfo fund) {
        logger.info("setHistoryNetAssetValue...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<HashMap<String, Object>> result = (List<HashMap<String, Object>>)js.executeScript("return window.Data_netWorthTrend;");
        List<AssetValue> assetValueList = result.stream().map((HashMap<String, Object> map) -> {
            AssetValue av = new AssetValue(Double.parseDouble(String.valueOf(map.get("y"))), DateUtils.getFormatDate((long)map.get("x")));
            av.setFund(fund);
            return av;
        }).toList();
        assetValueDao.insertAssetValueInBatch(assetValueList);
    }


    private void saveAllFunds(WebDriver driver, List<DataConfig> configs) throws InterruptedException {
        logger.info("saveAllDataConfig...");
        for(DataConfig dc: configs) {
            driver.get(dc.getResourceMainUrl());
            Thread.sleep(5000);
            FundInfo fund = dc.getFund();
            Document document = Jsoup.parse(driver.getPageSource());
            Elements columns = document.select(".infoOfFund td");
            String type = columns.get(0).text().split("：")[1].trim();
            String manager = columns.get(2).text().split("：")[1].trim();
            String establishDate = columns.get(3).text().split("：")[1].trim();
            fund.setEstablishDate(establishDate);
            fund.setType(type);
            fund.setManager(manager);
            fundRepository.save(fund);
            dataConfigRepository.save(dc);
            setLatestFundDetail(document, fund);
            setHistoryNetAssetValue(driver, fund);
        }
    }

    private void setLatestFundDetail(Document document, FundInfo fund) {
        LatestFundDetail lfd = new LatestFundDetail();
        lfd.setFund(fund);
        lfd.setCurrentNavDate(DateUtils.getFormatDate(new Date().getTime()));
        Elements dataItem01 = document.select(".dataItem01");
        lfd.setLatestNetAssetValue(Double.parseDouble(dataItem01.select("dd").get(0).select("span").get(0).text()));
        lfd.setLastOneMonthReturn(dataItem01.select("dd").get(1).select("span").get(1).text());
        lfd.setLastOneYearReturn(dataItem01.select("dd").get(2).select("span").get(1).text());
        Elements dataItem02 = document.select(".dataItem02");
        lfd.setLastThreeMonthReturn(dataItem02.select("dd").get(1).select("span").get(1).text());
        lfd.setLastThreeYearReturn(dataItem02.select("dd").get(2).select("span").get(1).text());
        Elements dataItem03 = document.select(".dataItem03");
        lfd.setLastSixMonthReturn(dataItem03.select("dd").get(1).select("span").get(1).text());
        lfd.setSinceEstablishmentReturn(dataItem03.select("dd").get(2).select("span").get(1).text());
        lfd.setHoldings(getAllStockHoldings(document, lfd));
        lfd.setCreateTimestamp(new Date());
        latestFundDetailRepository.save(lfd);
    }

    private List<StockHolding> getAllStockHoldings(Document document, LatestFundDetail lfd) {
        try {
            Elements holdingElms = Objects.requireNonNull(document.select(".poptableWrap").first()).select("tr");
            List<Element> filteredRows = holdingElms.subList(1, holdingElms.size());
            return filteredRows.stream().map((Element row) -> {
                StockHolding sh = new StockHolding();
                Elements cols = row.select("td");
                sh.setStockName(cols.get(0).text());
                sh.setPercent(cols.get(1).text());
                sh.setQuoteChange(cols.get(2).text());
                sh.setLatestFundDetail(lfd);
                return sh;
            }).toList();
        } catch(Exception e) {
            // should be no holding info, skip exception handling
            return null;
        }
    }

    @Scheduled(initialDelay = 1000 * 30, fixedDelay=Long.MAX_VALUE)
    public void performInitialLoading() throws InterruptedException {
        logger.info("Initial loading...");
        WebDriver driver = ScrapyUtils.getDriver();
        List<DataConfig> configs = getAllDataConfig(driver);
        dropPreviousData();
        saveAllFunds(driver, configs);
        ScrapyUtils.quit(driver);
    }

    @Scheduled(cron = "0 0 10-22 * * MON-FRI")
    public void performEveryWorkday() throws InterruptedException {
        logger.info("Perform every workday....");
        WebDriver driver = ScrapyUtils.getDriver();
        List<DataConfig> configs = dataConfigRepository.findAll();
        for(DataConfig dc: configs) {
            driver.get(dc.getResourceMainUrl());
            Thread.sleep(5000);
            FundInfo fund = dc.getFund();
            Document document = Jsoup.parse(driver.getPageSource());
            setLatestFundDetail(document, fund);
        }
        ScrapyUtils.quit(driver);
    }
    @Scheduled(cron = "0 0 23 * * MON-FRI")
    public void performAfterWorkday() {
        logger.info("Perform after workday....");
        List<FundInfo> fundInfoList = fundRepository.findAll();
        List<AssetValue> assetValueList = new ArrayList<>();
        fundInfoList.forEach((FundInfo fundInfo) -> {
            LatestFundDetail lfd = latestFundDetailRepository.findTopByFundIdOrderByCreateTimestampDesc(fundInfo.getId());
            AssetValue av = new AssetValue();
            av.setDate(lfd.getCurrentNavDate());
            av.setValue(lfd.getLatestNetAssetValue());
            av.setFund(fundInfo);
            assetValueList.add(av);
        });
        assetValueDao.insertAssetValueInBatch(assetValueList);
    }
}
