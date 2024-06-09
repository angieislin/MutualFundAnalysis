package com.anonymity.scraping.mutualfundanalysis.dto;

public class AssetValueDetail {
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

    private Double value;
    private String date;
}
