package com.pwc.aml.alert.entity;

import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.transation.entity.Transactions;

import java.util.List;

public class AlertCreation {

    private List<Transactions> transList;
    private Scenario scenario;

    public List<Transactions> getTransList() {
        return transList;
    }

    public void setTransList(List<Transactions> transList) {
        this.transList = transList;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
