package com.pwc.aml.accounts.dao;

import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.customers.entity.Customers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Transactional
@Repository
public class AccountDAO implements IAccountDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Accounts accounts) {
        em.persist(accounts);
    }

    @Override
    public Accounts update(Accounts accounts) {
        Accounts target = findByAcctId(accounts.getAccountId());
        accounts.setId(target.getId());
        Accounts cs = em.merge(accounts);
        return cs;
    }

    @Override
    public List<Accounts> findByCustId(String custId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Accounts> cq = cb.createQuery(Accounts.class);
        Root<Accounts> rootEntry = cq.from(Accounts.class);
        Predicate predicate = cb.equal(rootEntry.get("customerId"), custId);
        CriteriaQuery<Accounts> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Accounts> query = em.createQuery(single);
        List<Accounts> accounts = query.getResultList();
        return accounts;
    }

    @Override
    public Accounts findByAcctId(String accountId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Accounts> cq = cb.createQuery(Accounts.class);
        Root<Accounts> rootEntry = cq.from(Accounts.class);
        Predicate predicate = cb.equal(rootEntry.get("accountId"), accountId);
        CriteriaQuery<Accounts> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Accounts> query = em.createQuery(single);
        List<Accounts> accounts = query.getResultList();
        if (accounts.isEmpty()) {
            return null;
        } else {
            return accounts.get(0);
        }
    }
    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Accounts> cq = cb.createCriteriaDelete(Accounts.class);
        Root<Accounts> root = cq.from(Accounts.class);
        em.createQuery(cq).executeUpdate();
    }

    @Override
    public List<Accounts> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Accounts> cq = cb.createQuery(Accounts.class);
        Root<Accounts> rootEntry = cq.from(Accounts.class);
        CriteriaQuery<Accounts> all = cq.select(rootEntry);
        TypedQuery<Accounts> query = em.createQuery(all);
        List<Accounts> accounts = query.getResultList();
        return accounts;
    }
}
