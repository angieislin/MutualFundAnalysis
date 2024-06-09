package com.anonymity.scraping.mutualfundanalysis.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "latest_fund_detail")
public class LatestFundDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latestNetAssetValue;
    private String currentNavDate;
    private String lastOneMonthReturn;
    private String lastThreeMonthReturn;
    private String lastSixMonthReturn;
    private String lastOneYearReturn;
    private String lastThreeYearReturn;
    private String sinceEstablishmentReturn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;

    @OneToMany(mappedBy = "latestFundDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StockHolding> holdings;

    @ManyToOne
    @JoinColumn(name = "fund_id", foreignKey = @ForeignKey(name = "fk_lfd_fund_id",
            value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (fund_id) REFERENCES fund_info(id) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private FundInfo fund;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrentNavDate() {
        return currentNavDate;
    }

    public void setCurrentNavDate(String currentNavDate) {
        this.currentNavDate = currentNavDate;
    }

    public double getLatestNetAssetValue() {
        return latestNetAssetValue;
    }

    public void setLatestNetAssetValue(double latestNetAssetValue) {
        this.latestNetAssetValue = latestNetAssetValue;
    }

    public String getLastOneMonthReturn() {
        return lastOneMonthReturn;
    }

    public void setLastOneMonthReturn(String lastOneMonthReturn) {
        this.lastOneMonthReturn = lastOneMonthReturn;
    }

    public String getLastThreeMonthReturn() {
        return lastThreeMonthReturn;
    }

    public void setLastThreeMonthReturn(String lastThreeMonthReturn) {
        this.lastThreeMonthReturn = lastThreeMonthReturn;
    }

    public String getLastSixMonthReturn() {
        return lastSixMonthReturn;
    }

    public void setLastSixMonthReturn(String lastSixMonthReturn) {
        this.lastSixMonthReturn = lastSixMonthReturn;
    }

    public String getLastOneYearReturn() {
        return lastOneYearReturn;
    }

    public void setLastOneYearReturn(String lastOneYearReturn) {
        this.lastOneYearReturn = lastOneYearReturn;
    }

    public String getLastThreeYearReturn() {
        return lastThreeYearReturn;
    }

    public void setLastThreeYearReturn(String lastThreeYearReturn) {
        this.lastThreeYearReturn = lastThreeYearReturn;
    }

    public String getSinceEstablishmentReturn() {
        return sinceEstablishmentReturn;
    }

    public void setSinceEstablishmentReturn(String sinceEstablishmentReturn) {
        this.sinceEstablishmentReturn = sinceEstablishmentReturn;
    }

    public List<StockHolding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<StockHolding> holdings) {
        this.holdings = holdings;
    }


    public FundInfo getFund() {
        return fund;
    }

    public void setFund(FundInfo fund) {
        this.fund = fund;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
