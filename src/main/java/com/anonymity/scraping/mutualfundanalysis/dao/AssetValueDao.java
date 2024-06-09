package com.anonymity.scraping.mutualfundanalysis.dao;

import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;

import java.util.List;

public interface AssetValueDao {

    void insertAssetValueInBatch(List<AssetValue> values);

}
