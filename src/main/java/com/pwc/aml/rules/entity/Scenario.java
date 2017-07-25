package com.pwc.aml.rules.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.common.base.entity.BaseEntity;

@Entity
@Table(name="SCENARIO")
public class Scenario extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	@Column(name="SCENARIO_NAME")
	private String scenarioName;

	@Column(name="SCENARIO_CODE")
	private String scenarioCode;

	@Column(name="SCENARIO_CONTENT",length=4096)
	private String scenarioContent;

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getScenarioCode() {
		return scenarioCode;
	}

	public void setScenarioCode(String scenarioCode) {
		this.scenarioCode = scenarioCode;
	}

	public String getScenarioContent() {
		return scenarioContent;
	}

	public void setScenarioContent(String scenarioContent) {
		this.scenarioContent = scenarioContent;
	}
}
