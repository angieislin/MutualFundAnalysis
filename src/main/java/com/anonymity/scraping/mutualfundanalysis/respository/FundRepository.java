package com.anonymity.scraping.mutualfundanalysis.respository;

import com.anonymity.scraping.mutualfundanalysis.entity.FundInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<FundInfo, Long> {
    @Modifying
    @Transactional
    void deleteAllInBatch();
}
