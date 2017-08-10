package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Individual;
import com.pwc.aml.customers.entity.Individual;
import com.pwc.aml.customers.entity.Individual;
import com.pwc.aml.customers.entity.Individual;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
@Transactional
@Repository
public class IndividualDao implements IIndividualDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void save(Individual individual) {
        em.persist(individual);
    }

    @Override
    public Individual update(Individual individual) {
        Individual target = findByCustId(individual.getCustomerId());
        individual.setId(target.getId());
        Individual ind = em.merge(individual);
        return ind;
    }

    @Override
    public Individual findByCustId(String custId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Individual> cq = cb.createQuery(Individual.class);
        Root<Individual> rootEntry = cq.from(Individual.class);
        Predicate predicate = cb.equal(rootEntry.get("customerId"), custId);
        CriteriaQuery<Individual> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Individual> query = em.createQuery(single);
        List<Individual> individual = query.getResultList();
        if (individual.isEmpty()) {
            return null;
        } else {
            return individual.get(0);
        }
    }

    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Individual> cq = cb.createCriteriaDelete(Individual.class);
        Root<Individual> root = cq.from(Individual.class);
        em.createQuery(cq).executeUpdate();
    
    }

    @Override
    public List<Individual> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Individual> cq = cb.createQuery(Individual.class);
        Root<Individual> rootEntry = cq.from(Individual.class);
        CriteriaQuery<Individual> all = cq.select(rootEntry);
        TypedQuery<Individual> query = em.createQuery(all);
        List<Individual> individual = query.getResultList();
        return individual;
    }
}
