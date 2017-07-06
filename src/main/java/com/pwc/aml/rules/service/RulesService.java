package com.pwc.aml.rules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.rules.dao.IRulesDAO;
import com.pwc.aml.rules.entity.RuleScenario;

@Service
public class RulesService implements IRulesService{

	@Autowired
	private IRulesDAO rulesDAO;
	
	@Override
	public List<RuleScenario> listAllRuleScenario() {
		return rulesDAO.listAllRuleScenario();
	}

	@Override
	public RuleScenario findSingleRuleScenario(int scenarioId) {
		return rulesDAO.findSingleScenario(scenarioId);
	}

	@Override
	public void createRuleScenario(RuleScenario rScenario) {
		rulesDAO.createRules(rScenario);
	}

	@Override
	public void updateRuleScenario(RuleScenario rScenario) {
		rulesDAO.updateRules(rScenario);
	}

	@Override
	public void deleteRuleScenario(int scenarioId) {
		rulesDAO.deleteRules(scenarioId);
	}

}
