package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.CustomerBase;
import com.pwc.aml.customers.entity.Customers;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Repository
public class CustomerDAO implements ICustomerDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Customers customers) {
        em.persist(customers);
    }

    @Override
    public Customers update(Customers customers) {
        Customers target = findByCustId(customers.getCustomerId());
        customers.setId(target.getId());
        Customers cs = em.merge(customers);
        return cs;
    }

    @Override
    public Customers findByCustId(String custId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customers> cq = cb.createQuery(Customers.class);
        Root<Customers> rootEntry = cq.from(Customers.class);
        Predicate predicate = cb.equal(rootEntry.get("customerId"), custId);
        CriteriaQuery<Customers> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Customers> query = em.createQuery(single);
        List<Customers> customers = query.getResultList();
        if (customers.isEmpty()) {
            return null;
        } else {
            return customers.get(0);
        }
    }

    @Override
    public Customers findByCustCtNo(String CtNo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customers> cq = cb.createQuery(Customers.class);
        Root<Customers> rootEntry = cq.from(Customers.class);
        Predicate predicate = cb.equal(rootEntry.get("customerCertificateNumber"), CtNo);
        CriteriaQuery<Customers> single = cq.select(rootEntry).where(predicate);
        TypedQuery<Customers> query = em.createQuery(single);
        List<Customers> customers = query.getResultList();
        if (customers.isEmpty()) {
            return null;
        } else {
            return customers.get(0);
        }
    }

    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Customers> cq = cb.createCriteriaDelete(Customers.class);
        Root<Customers> root = cq.from(Customers.class);
        em.createQuery(cq).executeUpdate();
    }

    @Override
    public List<Customers> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customers> cq = cb.createQuery(Customers.class);
        Root<Customers> rootEntry = cq.from(Customers.class);
        CriteriaQuery<Customers> all = cq.select(rootEntry);
        TypedQuery<Customers> query = em.createQuery(all);
        List<Customers> customers = query.getResultList();
        return customers;
    }

    @Override
    public List<String> findByIdAndName(String customerId, String customerName) {
        String hql = null;

        List<CustomerBase> list = new ArrayList<CustomerBase>();
        if(StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(customerName)){
            hql = "FROM CustomerBase WHERE customerId LIKE ? AND customerFullName LIKE ?";
            list = em.createQuery(hql).setParameter(1, "%"+customerId+"%")
                    .setParameter(2, "%"+customerName+"%").getResultList();
        }else if(StringUtils.isNotBlank(customerId) && StringUtils.isBlank(customerName)){
            hql = "FROM CustomerBase WHERE customerId LIKE ?";
            list = em.createQuery(hql).setParameter(1, "%"+customerId+"%").getResultList();
        }else if(StringUtils.isBlank(customerId) && StringUtils.isNotBlank(customerName)){
            hql = "FROM CustomerBase WHERE customerFullName LIKE ?";
            list = em.createQuery(hql).setParameter(1, "%"+customerName+"%").getResultList();
        }else{
            hql = "FROM CustomerBase";
            list = em.createQuery(hql).getResultList();
        }

        if(list.size()>0){
            List<String> customIdList = new ArrayList<String>();
            for(CustomerBase cb : list){
                customIdList.add(cb.getCustomerId());
            }
            return customIdList;
        }
        return null;
    }
}
