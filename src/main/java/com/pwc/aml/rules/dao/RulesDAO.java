package com.pwc.aml.rules.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.rules.entity.RuleScenario;

@Transactional
@Repository
public class RulesDAO implements IRulesDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RuleScenario> listAllRuleScenario() {
		String hql = "FROM RuleScenario ORDER BY scenarioId";
        return (List<RuleScenario>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void createRules(RuleScenario rScenario) {
		entityManager.persist(rScenario);
	}

	@Override
	public void updateRules(RuleScenario rScenario) {
		RuleScenario rs = this.findSingleScenario(rScenario.getId());
		rs.setScenarioName(rScenario.getScenarioName());
		entityManager.flush();
	}

	@Override
	public void deleteRules(int scenarioId) {
		entityManager.remove(this.findSingleScenario(scenarioId));
	}

	@Override
	public RuleScenario findSingleScenario(int scenarioId) {
		return entityManager.find(RuleScenario.class, scenarioId);
	}
	
	
	
}
