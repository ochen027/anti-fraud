package com.pwc.aml.rules.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void createRules(Scenario rScenario) {
		entityManager.persist(rScenario);
	}

	@Override
	public void updateRules(Scenario rScenario) {
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
	

}
