package com.pwc.aml.rules.dao;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.Rules;
import com.pwc.aml.rules.entity.Scenario;

public interface IRulesDAO {
	
	List<Scenario> listAllRuleScenario();
	
	void createScenario(Scenario rScenario);
	
	void updateScenario(Scenario rScenario);
	
	void deleteRules(int scenarioId);
	
	Scenario findSingleScenario(int scenarioId);


	void createRules(Rules rules);

	void updateRules(Rules rules);

	void createRuleScenario(RuleScenario ruleScenario);

	void updateRuleScenario(RuleScenario ruleScenario);

	void deleteRuleScenarioByRuleId(int id);
}
