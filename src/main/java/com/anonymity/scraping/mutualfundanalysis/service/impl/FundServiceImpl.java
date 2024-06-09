package com.anonymity.scraping.mutualfundanalysis.service.impl;

import com.anonymity.scraping.mutualfundanalysis.dto.AssetValueDetail;
import com.anonymity.scraping.mutualfundanalysis.dto.FundDetail;
import com.anonymity.scraping.mutualfundanalysis.dto.FundPreview;
import com.anonymity.scraping.mutualfundanalysis.dto.StockHoldingDetail;
import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;
import com.anonymity.scraping.mutualfundanalysis.entity.FundInfo;
import com.anonymity.scraping.mutualfundanalysis.entity.LatestFundDetail;
import com.anonymity.scraping.mutualfundanalysis.entity.StockHolding;
import com.anonymity.scraping.mutualfundanalysis.respository.AssetValueRepository;
import com.anonymity.scraping.mutualfundanalysis.respository.FundRepository;
import com.anonymity.scraping.mutualfundanalysis.respository.LatestFundDetailRepository;
import com.anonymity.scraping.mutualfundanalysis.service.FundService;
import com.anonymity.scraping.mutualfundanalysis.utils.GenAiUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FundServiceImpl implements FundService {
    private static final Logger logger = LoggerFactory.getLogger(FundServiceImpl.class);
    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private LatestFundDetailRepository latestFundDetailRepository;
    @Autowired
    private AssetValueRepository assetValueRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${openai.api.key}")
    private String apiKey;

    public List<FundPreview> getAllFunds() {
        logger.info("getAllFunds....");
        return fundRepository.findAll().stream().map(this::convertFundInfoToDto).collect(Collectors.toList());
    }

    @Override
    public FundDetail getFundDetail(Long fundId) {
        logger.info("getFundDetail....");
        List<AssetValue> rawAssetValue = assetValueRepository.findByFundId(fundId);
        LatestFundDetail lfd = latestFundDetailRepository.findTopByFundIdOrderByCreateTimestampDesc(fundId);
        FundInfo fundInfo = rawAssetValue.get(0).getFund();
        FundDetail fd = convertFundDetailToDto(fundInfo, lfd);
        fd.setNetAssetValues(rawAssetValue.stream().map(this::convertAssetValueToDto).collect(Collectors.toList()));
        fd.setStockHoldings(lfd.getHoldings().stream().map(this::convertHoldingToDto).collect(Collectors.toList()));
        return fd;
    }

    @Override
    public String getOverviewIdea() {
        return GenAiUtils.getOverallIdea(apiKey);
    }

    private FundPreview convertFundInfoToDto(FundInfo fund) {
        FundPreview fundPreview = modelMapper.map(fund, FundPreview.class);
        return fundPreview;
    }

    private AssetValueDetail convertAssetValueToDto(AssetValue av) {
        AssetValueDetail avd = modelMapper.map(av, AssetValueDetail.class);
        return avd;
    }

    private FundDetail convertFundDetailToDto(FundInfo fund, LatestFundDetail lfd) {
        FundDetail fundDetail = new FundDetail();
        modelMapper.map(fund, fundDetail);
        modelMapper.map(lfd, fundDetail);
        return fundDetail;
    }

    private StockHoldingDetail convertHoldingToDto(StockHolding sh) {
        StockHoldingDetail holding = modelMapper.map(sh, StockHoldingDetail.class);
        return holding;
    }
}
