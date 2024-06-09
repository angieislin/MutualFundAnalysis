package com.anonymity.scraping.mutualfundanalysis.service;

import com.anonymity.scraping.mutualfundanalysis.dto.FundDetail;
import com.anonymity.scraping.mutualfundanalysis.dto.FundPreview;

import java.util.List;



public interface FundService {
    List<FundPreview> getAllFunds();

    FundDetail getFundDetail(Long fundId);

    String getOverviewIdea();


}
