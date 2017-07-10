package com.pwc.aml.rules.dao;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.RuleStep;

public interface IRulesDAO {
	
	List<RuleScenario> listAllRuleScenario();
	
	void createRules(RuleScenario rScenario);
	
	void updateRules(RuleScenario rScenario);
	
	void deleteRules(int scenarioId);
	
	RuleScenario findSingleScenario(int scenarioId);
	
	void createStep(RuleStep rStep);
}
