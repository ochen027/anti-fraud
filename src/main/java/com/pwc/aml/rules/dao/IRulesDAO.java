package com.pwc.aml.rules.dao;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;

public interface IRulesDAO {
	
	public List<RuleScenario> listAllRuleScenario();
	
	public void createRules(RuleScenario rScenario);
	
	public void updateRules(RuleScenario rScenario);
	
	public void deleteRules(int scenarioId);
	
	public RuleScenario findSingleScenario(int scenarioId);

}
