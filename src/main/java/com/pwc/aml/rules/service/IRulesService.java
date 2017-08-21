package com.pwc.aml.rules.service;

import java.util.List;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.Rules;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.component.authorize.users.entity.Users;

public interface IRulesService {
	
	List<Scenario> listAllRuleScenario();
	
	Scenario findSingleRuleScenario(int scenarioId);
	
	void createRuleScenario(Scenario rScenario);
	
	void updateRuleScenario(Scenario rScenario);
	
	void deleteRuleScenario(int scenarioId);
	
	//String getRuleScript(int scenarioId);
	
	void executeRuleEngine(int scenarioId, String userName) throws Exception;

	Scenario saveOrUpdate(Scenario scenario, Users users);

    Rules saveOrUpdateRules(Rules rules, Users users);

    List<Rules> listAllRules();

    List<RuleScenario> findRuleScenarioByRuleId(int ruleId);

    String getDefaultRuleId();

    void setDefaultRuleId(String ruleId, String userName);

    void runRule(int ruleId,String userName);
}
