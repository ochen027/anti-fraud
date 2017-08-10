package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.CustomerBase;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Transactional
@Repository
public class CustomerBaseDao implements ICustomerBaseDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CustomerBase customerBase) {
        em.persist(customerBase);
    }

    @Override
    public CustomerBase update(CustomerBase customerBase) {
        CustomerBase target = findByCustId(customerBase.getCustomerId());
        customerBase.setId(target.getId());
        CustomerBase cs = em.merge(customerBase);
        return cs;
    }

    @Override
    public CustomerBase findByCustId(String custId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerBase> cq = cb.createQuery(CustomerBase.class);
        Root<CustomerBase> rootEntry = cq.from(CustomerBase.class);
        Predicate predicate = cb.equal(rootEntry.get("customerId"), custId);
        CriteriaQuery<CustomerBase> single = cq.select(rootEntry).where(predicate);
        TypedQuery<CustomerBase> query = em.createQuery(single);
        List<CustomerBase> customerBase = query.getResultList();
        if (customerBase.isEmpty()) {
            return null;
        } else {
            return customerBase.get(0);
        }
    }

    @Override
    public CustomerBase findByCustCtNo(String CtNo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerBase> cq = cb.createQuery(CustomerBase.class);
        Root<CustomerBase> rootEntry = cq.from(CustomerBase.class);
        Predicate predicate = cb.equal(rootEntry.get("customerBaseCertificateNumber"), CtNo);
        CriteriaQuery<CustomerBase> single = cq.select(rootEntry).where(predicate);
        TypedQuery<CustomerBase> query = em.createQuery(single);
        List<CustomerBase> customerBase = query.getResultList();
        if (customerBase.isEmpty()) {
            return null;
        } else {
            return customerBase.get(0);
        }
    }

    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<CustomerBase> cq = cb.createCriteriaDelete(CustomerBase.class);
        Root<CustomerBase> root = cq.from(CustomerBase.class);
        em.createQuery(cq).executeUpdate();
    }

    @Override
    public List<CustomerBase> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerBase> cq = cb.createQuery(CustomerBase.class);
        Root<CustomerBase> rootEntry = cq.from(CustomerBase.class);
        CriteriaQuery<CustomerBase> all = cq.select(rootEntry);
        TypedQuery<CustomerBase> query = em.createQuery(all);
        List<CustomerBase> customerBase = query.getResultList();
        return customerBase;
    }
}
