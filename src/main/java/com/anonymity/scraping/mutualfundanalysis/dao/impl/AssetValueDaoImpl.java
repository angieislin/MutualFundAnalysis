package com.anonymity.scraping.mutualfundanalysis.dao.impl;

import com.anonymity.scraping.mutualfundanalysis.dao.AssetValueDao;
import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;
import com.anonymity.scraping.mutualfundanalysis.entity.FundInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetValueDaoImpl implements AssetValueDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertAssetValueInBatch(List<AssetValue> values) {
        String sql = "insert into asset_values(value, date, fund_id) values(?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, values, 50, (ps, assetValue) -> {
            ps.setDouble(1, assetValue.getValue());
            ps.setString(2, assetValue.getDate());
            ps.setLong(3, assetValue.getFund().getId());
        });
    }
}
