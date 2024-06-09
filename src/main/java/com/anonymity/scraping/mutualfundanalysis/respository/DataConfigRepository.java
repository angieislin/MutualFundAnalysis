package com.anonymity.scraping.mutualfundanalysis.respository;

import com.anonymity.scraping.mutualfundanalysis.entity.DataConfig;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface DataConfigRepository extends JpaRepository<DataConfig, Long> {
}
