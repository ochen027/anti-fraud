package com.pwc.aml.rules.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pwc.aml.common.base.entity.BaseEntity;

@Entity
@Table(name="RULESTEP")
public class RuleStep extends BaseEntity{

	private static final long serialVersionUID = 1L;
	@Column(name="STEP_NAME")
	private String stepName;
	@Column(name="STEP_ORDER")
	private int stepOrder;
	@Column(name="STEP_WHEN")
	private String stepWhen;
	@Column(name="STEP_THEN")
	private String stepThen;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SCENARIO_ID", nullable = false)
	private RuleScenario ruleScenario;
	
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public int getStepOrder() {
		return stepOrder;
	}
	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}
	public String getStepWhen() {
		return stepWhen;
	}
	public void setStepWhen(String stepWhen) {
		this.stepWhen = stepWhen;
	}
	public String getStepThen() {
		return stepThen;
	}
	public void setStepThen(String stepThen) {
		this.stepThen = stepThen;
	}
	public RuleScenario getRuleScenario() {
		return ruleScenario;
	}
	public void setRuleScenario(RuleScenario ruleScenario) {
		this.ruleScenario = ruleScenario;
	}

}
