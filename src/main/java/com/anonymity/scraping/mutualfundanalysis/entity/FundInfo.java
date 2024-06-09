package com.anonymity.scraping.mutualfundanalysis.entity;

import com.anonymity.scraping.mutualfundanalysis.entity.AssetValue;
import jakarta.persistence.*;


@Entity
@Table(name = "fund_info")
public class FundInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String manager;
    private String establishDate;
    private String type;

    public FundInfo() {
    }

    public FundInfo(String code,  String name, String manager) {
        this.name = name;
        this.code = code;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
