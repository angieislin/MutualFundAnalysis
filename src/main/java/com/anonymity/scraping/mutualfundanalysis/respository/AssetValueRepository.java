package com.anonymity.scraping.mutualfundanalysis.respository;

import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetValueRepository extends JpaRepository<AssetValue, Long> {
    @EntityGraph(attributePaths = {"fund"})
    List<AssetValue> findByFundId(Long fundId);
}
