package com.anonymity.scraping.mutualfundanalysis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asset_values")
public class AssetValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", foreignKey = @ForeignKey(name = "fk_av_fund_id",
            value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (fund_id) REFERENCES fund_info(id) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private FundInfo fund;

    // Constructors
    public AssetValue() {
    }

    public AssetValue(Double value, String date) {
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FundInfo getFund() {
        return fund;
    }

    public void setFund(FundInfo fund) {
        this.fund = fund;
    }
}
