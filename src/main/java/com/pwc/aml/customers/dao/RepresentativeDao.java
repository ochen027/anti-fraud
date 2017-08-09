package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Representative;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
@Transactional
@Repository
public class RepresentativeDao implements IRepresentativeDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void save(Representative representative) {
        em.persist(representative);

    }

    @Override
    public Representative update(Representative representative) {
        Representative target = findByCustId(representative.getCustomerId());
        representative.setId(target.getId());
        Representative rep = em.merge(representative);
        return rep;
    }

    @Override
    public Representative findByCustId(String custId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Representative> cq = cb.createQuery(Representative.class);
        Root<Representative> rootEntry = cq.from(Representative.class);
        Predicate predicate = cb.equal(rootEntry.get("customerId"), custId);
        CriteriaQuery<Representative> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Representative> query = em.createQuery(single);
        List<Representative> representative = query.getResultList();
        if (representative.isEmpty()) {
            return null;
        } else {
            return representative.get(0);
        }
    }

    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Representative> cq = cb.createCriteriaDelete(Representative.class);
        Root<Representative> root = cq.from(Representative.class);
        em.createQuery(cq).executeUpdate();

    }

    @Override
    public List<Representative> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Representative> cq = cb.createQuery(Representative.class);
        Root<Representative> rootEntry = cq.from(Representative.class);
        CriteriaQuery<Representative> all = cq.select(rootEntry);
        TypedQuery<Representative> query = em.createQuery(all);
        List<Representative> representative = query.getResultList();
        return representative;
    }
}
