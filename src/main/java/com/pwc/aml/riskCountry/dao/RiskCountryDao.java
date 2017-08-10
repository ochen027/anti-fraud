package com.pwc.aml.riskCountry.dao;

import com.pwc.aml.riskCountry.entity.RiskCountry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Transactional
@Repository
public class RiskCountryDao implements IRiskCountryDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public RiskCountry save(RiskCountry riskCountry) {
         em.persist(riskCountry);
         return riskCountry;
    }

    @Override
    public RiskCountry update(RiskCountry riskCountry) {
        return em.merge(riskCountry);
    }

    @Override
    public void deleteById(int id) {
        RiskCountry rc=em.find(RiskCountry.class, id);
        rc.setStatus(false);
        em.merge(rc);
        em.flush();
    }
    @Override
    public void delete(RiskCountry rc){
        RiskCountry target=em.find(RiskCountry.class, rc.getId());
       em.remove(target);
    }

    @Override
    public List<RiskCountry> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RiskCountry> cq = cb.createQuery(RiskCountry.class);
        Root<RiskCountry> rootEntry = cq.from(RiskCountry.class);
        CriteriaQuery<RiskCountry> all = cq.select(rootEntry);
        TypedQuery<RiskCountry> query = em.createQuery(all);
        return query.getResultList();
    }

    @Override
    public RiskCountry findById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RiskCountry> cq = cb.createQuery(RiskCountry.class);
        Root<RiskCountry> rootEntry = cq.from(RiskCountry.class);
        Predicate predicate = cb.equal(rootEntry.get("id"), id);
        CriteriaQuery<RiskCountry> single = cq.select(rootEntry).where(predicate);
        TypedQuery<RiskCountry> query = em.createQuery(single);
        List<RiskCountry> rcs = query.getResultList();
        if (rcs.isEmpty()) {
            return null;
        } else {
            return rcs.get(0);
        }

    }
    @Override
    public void removeAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<RiskCountry> cq = cb.createCriteriaDelete(RiskCountry.class);
        Root<RiskCountry> root = cq.from(RiskCountry.class);
        em.createQuery(cq).executeUpdate();

    }
}
