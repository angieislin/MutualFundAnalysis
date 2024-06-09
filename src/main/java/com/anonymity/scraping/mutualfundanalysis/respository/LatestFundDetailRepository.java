package com.anonymity.scraping.mutualfundanalysis.respository;

import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;
import com.anonymity.scraping.mutualfundanalysis.entity.LatestFundDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LatestFundDetailRepository extends JpaRepository<LatestFundDetail, Long> {
    @EntityGraph(attributePaths = {"fund"})
    LatestFundDetail findTopByFundIdOrderByCreateTimestampDesc(Long fundId);
}
