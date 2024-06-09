package com.anonymity.scraping.mutualfundanalysis.dto;

import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;

import java.util.List;

public class FundDetail extends FundPreview {
    private String manager;
    private String establishDate;
    private String type;

    private double latestNetAssetValue;
    private String currentNavDate;
    private String lastOneMonthReturn;
    private String lastThreeMonthReturn;
    private String lastSixMonthReturn;
    private String lastOneYearReturn;
    private String lastThreeYearReturn;
    private String sinceEstablishmentReturn;

    private List<StockHoldingDetail> stockHoldings;

    private List<AssetValueDetail> netAssetValues;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AssetValueDetail> getNetAssetValues() {
        return netAssetValues;
    }

    public void setNetAssetValues(List<AssetValueDetail> netAssetValues) {
        this.netAssetValues = netAssetValues;
    }

    public double getLatestNetAssetValue() {
        return latestNetAssetValue;
    }

    public void setLatestNetAssetValue(double latestNetAssetValue) {
        this.latestNetAssetValue = latestNetAssetValue;
    }

    public String getCurrentNavDate() {
        return currentNavDate;
    }

    public void setCurrentNavDate(String currentNavDate) {
        this.currentNavDate = currentNavDate;
    }

    public String getLastOneMonthReturn() {
        return lastOneMonthReturn;
    }

    public void setLastOneMonthReturn(String lastOneMonthReturn) {
        this.lastOneMonthReturn = lastOneMonthReturn;
    }

    public String getLastThreeMonthReturn() {
        return lastThreeMonthReturn;
    }

    public void setLastThreeMonthReturn(String lastThreeMonthReturn) {
        this.lastThreeMonthReturn = lastThreeMonthReturn;
    }

    public String getLastSixMonthReturn() {
        return lastSixMonthReturn;
    }

    public void setLastSixMonthReturn(String lastSixMonthReturn) {
        this.lastSixMonthReturn = lastSixMonthReturn;
    }

    public String getLastOneYearReturn() {
        return lastOneYearReturn;
    }

    public void setLastOneYearReturn(String lastOneYearReturn) {
        this.lastOneYearReturn = lastOneYearReturn;
    }

    public String getLastThreeYearReturn() {
        return lastThreeYearReturn;
    }

    public void setLastThreeYearReturn(String lastThreeYearReturn) {
        this.lastThreeYearReturn = lastThreeYearReturn;
    }

    public String getSinceEstablishmentReturn() {
        return sinceEstablishmentReturn;
    }

    public void setSinceEstablishmentReturn(String sinceEstablishmentReturn) {
        this.sinceEstablishmentReturn = sinceEstablishmentReturn;
    }

    public List<StockHoldingDetail> getStockHoldings() {
        return stockHoldings;
    }

    public void setStockHoldings(List<StockHoldingDetail> stockHoldings) {
        this.stockHoldings = stockHoldings;
    }
}
