package com.pwc.aml.suppress.entity;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.customers.entity.CustomerBase;

import com.pwc.aml.rules.entity.Scenario;
import com.pwc.common.base.entity.BaseEntity;

import java.util.Date;


public class Suppress  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String suppressId;

    private CustomerBase customerBase;

    private Scenario scenario;

    private Alerts alerts;

    private boolean isPermanent;

    private Date endDate;

    public CustomerBase getCustomerBase() {
        return customerBase;
    }

    public void setCustomerBase(CustomerBase customerBase) {
        this.customerBase = customerBase;
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

    public Alerts getAlerts() {
        return alerts;
    }

    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }
}
