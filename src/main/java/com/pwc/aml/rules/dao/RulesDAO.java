package com.pwc.aml.rules.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.Rules;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.rules.entity.Scenario;

@Transactional
@Repository
public class RulesDAO implements IRulesDAO {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Scenario> listAllRuleScenario() {
		String hql = "FROM Scenario where status = 1 ORDER BY id";
        return (List<Scenario>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void createScenario(Scenario rScenario) {
		entityManager.persist(rScenario);
	}

	@Override
	public void updateScenario(Scenario rScenario) {
		Scenario rs = this.findSingleScenario(rScenario.getId());
		rs.setScenarioName(rScenario.getScenarioName());
		rs.setScenarioContent(rScenario.getScenarioContent());
		rs.setScenarioCode(rScenario.getScenarioCode());
		entityManager.flush();
	}

	@Override
	public void deleteRules(int scenarioId) {
		Scenario rs = this.findSingleScenario(scenarioId);
		rs.setStatus(false);
		entityManager.flush();
	}

	@Override
	public Scenario findSingleScenario(int scenarioId) {
		return entityManager.find(Scenario.class, scenarioId);
	}

	@Override
	public void createRules(Rules rules) {
		entityManager.persist(rules);
	}

	@Override
	public void updateRules(Rules rules) {
		entityManager.merge(rules);
	}

	@Override
	public void createRuleScenario(RuleScenario ruleScenario) {
		entityManager.persist(ruleScenario);
	}

	@Override
	public void updateRuleScenario(RuleScenario ruleScenario) {
		entityManager.merge(ruleScenario);
	}

	@Override
	public void deleteRuleScenarioByRuleId(int id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<RuleScenario> cq = cb.createCriteriaDelete(RuleScenario.class);
		Root<RuleScenario> root = cq.from(RuleScenario.class);
		cq.where(cb.equal(root.get("ruleId"), id));
		entityManager.createQuery(cq).executeUpdate();
	}


}
