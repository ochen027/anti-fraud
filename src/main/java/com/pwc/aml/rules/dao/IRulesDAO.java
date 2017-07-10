package com.pwc.aml.rules.dao;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;

public interface IRulesDAO {
	
	List<RuleScenario> listAllRuleScenario();
	
	void createRules(RuleScenario rScenario);
	
	void updateRules(RuleScenario rScenario);
	
	void deleteRules(int scenarioId);
	
	public RuleScenario findSingleScenario(int scenarioId);

}
