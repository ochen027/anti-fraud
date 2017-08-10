package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Corporate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
@Transactional
@Repository
public class CorporateDao implements ICorporateDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void save(Corporate corporate) {
    em.persist(corporate);
    }

    @Override
    public Corporate update(Corporate corporate) {
        Corporate target = findByCustId(corporate.getCustomerId());
        corporate.setId(target.getId());
        Corporate cor = em.merge(corporate);
        return cor;
    }

    @Override
    public Corporate findByCustId(String custId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Corporate> cq = cb.createQuery(Corporate.class);
        Root<Corporate> rootEntry = cq.from(Corporate.class);
        Predicate predicate = cb.equal(rootEntry.get("customerId"), custId);
        CriteriaQuery<Corporate> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Corporate> query = em.createQuery(single);
        List<Corporate> corporate = query.getResultList();
        if (corporate.isEmpty()) {
            return null;
        } else {
            return corporate.get(0);
        }
    }

    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Corporate> cq = cb.createCriteriaDelete(Corporate.class);
        Root<Corporate> root = cq.from(Corporate.class);
        em.createQuery(cq).executeUpdate();
    }

    @Override
    public List<Corporate> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Corporate> cq = cb.createQuery(Corporate.class);
        Root<Corporate> rootEntry = cq.from(Corporate.class);
        CriteriaQuery<Corporate> all = cq.select(rootEntry);
        TypedQuery<Corporate> query = em.createQuery(all);
        List<Corporate> corporate = query.getResultList();
        return corporate;
    }
}
