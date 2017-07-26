package com.pwc.aml.rules.dao;

import java.util.List;

import com.pwc.aml.rules.entity.Scenario;

public interface IRulesDAO {
	
	List<Scenario> listAllRuleScenario();
	
	void createRules(Scenario rScenario);
	
	void updateRules(Scenario rScenario);
	
	void deleteRules(int scenarioId);
	
	Scenario findSingleScenario(int scenarioId);





}
