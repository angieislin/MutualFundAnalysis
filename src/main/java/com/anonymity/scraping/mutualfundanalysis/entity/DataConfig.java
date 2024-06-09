package com.anonymity.scraping.mutualfundanalysis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "data_config")
public class DataConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fund_id" , foreignKey = @ForeignKey(name = "fk_dc_fund_id",
            value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (fund_id) REFERENCES fund_info(id) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private FundInfo fund;

    private String resourceMainUrl;

    public Long getId() {
        return id;
    }

    public FundInfo getFund() {
        return fund;
    }

    public String getResourceMainUrl() {
        return resourceMainUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFund(FundInfo fund) {
        this.fund = fund;
    }

    public void setResourceMainUrl(String resourceMainUrl) {
        this.resourceMainUrl = resourceMainUrl;
    }

    public DataConfig() {}

    public DataConfig(FundInfo fund, String resourceMainUrl) {
        this.fund = fund;
        this.resourceMainUrl = resourceMainUrl;
    }
}
