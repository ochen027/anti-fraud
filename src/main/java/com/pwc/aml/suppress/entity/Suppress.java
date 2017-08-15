package com.pwc.aml.suppress.entity;

import com.pwc.aml.customers.entity.Customers;

import com.pwc.aml.rules.entity.Scenario;
import com.pwc.common.base.entity.BaseEntity;

import java.util.Date;


public class Suppress  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String suppressId;

    private Customers customers;

    private Scenario scenario;

    private boolean isPermanent;

    private Date endDate;

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSuppressId() {
        return suppressId;
    }

    public void setSuppressId(String suppressId) {
        this.suppressId = suppressId;
    }
}
