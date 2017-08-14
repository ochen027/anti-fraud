package com.pwc.aml.suppress.entity;

import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.rules.entity.Rules;
import com.pwc.common.base.entity.BaseEntity;

import java.util.Date;


public class Suppress  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Customers customers;

    private Rules rules;

    private boolean isPermanent;

    private String endDate;


    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
