package com.pwc.aml.riskCountry.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RiskCountry")
public class RiskCountry extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "REGION")
    private String region;

    @Column(name = "RISK")
    private String risk;

    @Column(name = "NAME")
    private String name;


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
