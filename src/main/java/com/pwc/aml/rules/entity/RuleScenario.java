package com.pwc.aml.rules.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RULESCENARIO")
public class RuleScenario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="SCENARIO_ID")
	private int scenarioId;
	@Column(name="SCENARIO_NAME")
	private String scenarioName;
	public int getScenarioId() {
		return scenarioId;
	}
	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

}
