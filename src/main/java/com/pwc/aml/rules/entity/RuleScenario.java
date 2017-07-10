package com.pwc.aml.rules.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.aml.base.entity.BaseEntity;

@Entity
@Table(name="RULESCENARIO")
public class RuleScenario extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	@Column(name="SCENARIO_NAME")
	private String scenarioName;
	
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

}
