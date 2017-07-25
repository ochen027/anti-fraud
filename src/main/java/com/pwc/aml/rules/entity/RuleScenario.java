package com.pwc.aml.rules.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="RULE_SCENARIO")
public class RuleScenario extends BaseEntity{

    @Column(name ="RULE_ID")
    private int ruleId;

    @Column(name="SCENARIO_ID")
    private int scenarioId;

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }
}
