package com.pwc.aml.rules.service;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.RuleStep;

public interface IRulesService {
	
	List<RuleScenario> listAllRuleScenario();
	
	RuleScenario findSingleRuleScenario(int scenarioId);
	
	void createRuleScenario(RuleScenario rScenario);
	
	void updateRuleScenario(RuleScenario rScenario);
	
	void deleteRuleScenario(int scenarioId);
	
	void createRuleStep(RuleStep rStep);

}
