package com.pwc.aml.rules.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="RULES")
public class Rules extends BaseEntity {

    @Column(name="RULE_NAME")
    private String ruleName;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
