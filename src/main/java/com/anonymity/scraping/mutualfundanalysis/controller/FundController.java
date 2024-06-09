package com.anonymity.scraping.mutualfundanalysis.controller;

import com.anonymity.scraping.mutualfundanalysis.dto.FundDetail;
import com.anonymity.scraping.mutualfundanalysis.dto.FundPreview;
import com.anonymity.scraping.mutualfundanalysis.service.FundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FundController {
    private static final Logger logger = LoggerFactory.getLogger(FundController.class);
    @Autowired
    private FundService fundService;

    @GetMapping("/funds")
    public List<FundPreview> getFunds() {
        logger.info("getFunds...");
        return fundService.getAllFunds();
    }

    @GetMapping("/funds/{fundId}")
    public FundDetail getFundById(@PathVariable Long fundId) {
        logger.info("getFundById...");
        return fundService.getFundDetail(fundId);
    }
}
