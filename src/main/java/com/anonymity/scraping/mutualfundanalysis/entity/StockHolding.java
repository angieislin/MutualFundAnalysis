package com.anonymity.scraping.mutualfundanalysis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_holding")
public class StockHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;
    private String percent;
    private String quoteChange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lfd_id", foreignKey = @ForeignKey(name = "fk_lfd_holding_id",
            value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (lfd_id) REFERENCES latest_fund_detail(id) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private LatestFundDetail latestFundDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getQuoteChange() {
        return quoteChange;
    }

    public void setQuoteChange(String quoteChange) {
        this.quoteChange = quoteChange;
    }

    public LatestFundDetail getLatestFundDetail() {
        return latestFundDetail;
    }

    public void setLatestFundDetail(LatestFundDetail latestFundDetail) {
        this.latestFundDetail = latestFundDetail;
    }
}
