package com.pwc.aml.rules.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.RuleStep;

@Transactional
@Repository
public class RulesDAO implements IRulesDAO {
	
	
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<RuleScenario> listAllRuleScenario() {
		String hql = "FROM RuleScenario where status = 1 ORDER BY id";
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

	@Override
	public void createStep(RuleStep rStep) {
		entityManager.persist(rStep);
	}

	@Override
	public List<RuleStep> listStepByRule(int scenarioId) {
		String hql = "select rs.stepName, rs.stepOrder, rs.stepWhen, rs.stepThen from RuleStep as rs, RuleScenario rso where rso.id = ? order by rs.stepOrder";
		List<Object[]> list = entityManager.createQuery(hql).setParameter(1, scenarioId).getResultList();
		List<RuleStep> rList = new ArrayList<RuleStep>();
		Iterator<Object []> iter = list.iterator();
		
		while(iter.hasNext()){
			RuleStep rs = new RuleStep();
			Object[] obj = iter.next();
			rs.setStepName((String)obj[0]);
			rs.setStepOrder((Integer)obj[1]);
			rs.setStepThen((String)obj[2]);
			rs.setStepWhen((String)obj[3]);
			rList.add(rs);
		}
		return rList;
		
	}
	

	

}
