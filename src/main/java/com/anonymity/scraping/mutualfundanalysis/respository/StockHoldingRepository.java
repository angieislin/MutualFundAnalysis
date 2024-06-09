package com.anonymity.scraping.mutualfundanalysis.respository;

import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;
import com.anonymity.scraping.mutualfundanalysis.entity.StockHolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHoldingRepository  extends JpaRepository<StockHolding, Long> {
}
