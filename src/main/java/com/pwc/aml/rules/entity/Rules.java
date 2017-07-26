package com.pwc.aml.rules.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="RULES")
public class Rules extends BaseEntity {

    @Column(name="RULE_NAME")
    private String ruleName;

    @Column(name="DESCRIPTION")
    private String description;

    @Transient
    private List<Integer> scenarios;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Integer> scenarios) {
        this.scenarios = scenarios;
    }
}
