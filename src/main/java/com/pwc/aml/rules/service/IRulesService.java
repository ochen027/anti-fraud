package com.pwc.aml.rules.service;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;

public interface IRulesService {
	
	public List<RuleScenario> listAllRuleScenario();
	
	public RuleScenario findSingleRuleScenario(int scenarioId);
	
	public void createRuleScenario(RuleScenario rScenario);
	
	public void updateRuleScenario(RuleScenario rScenario);
	
	public void deleteRuleScenario(int scenarioId);

}
