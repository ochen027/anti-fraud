package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.CustomerBase;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public CustomerBase findByAccountId(String accountId) {
        String sql = "SELECT c.cust_id, c.full_nm from Accounts a, Customers c where a.cust_id = c.cust_id and a.acct_id = ?";
        List list = em.createNativeQuery(sql).setParameter(1, accountId).getResultList();
        Iterator iter = list.iterator();
        CustomerBase customerBase = new CustomerBase();
        while(iter.hasNext()){
            Object[] obj = (Object[]) iter.next();
            customerBase.setCustomerId((String)obj[0]);
            customerBase.setCustomerFullName((String)obj[1]);
        }
        return customerBase;
    }

    @Override
    public List<String> findHighRiskCustomer() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerBase> cq = cb.createQuery(CustomerBase.class);
        Root<CustomerBase> rootEntry = cq.from(CustomerBase.class);
        Predicate predicate = cb.equal(rootEntry.get("customerRiskLevel"), "H");
        CriteriaQuery<CustomerBase> single = cq.select(rootEntry).where(predicate);
        TypedQuery<CustomerBase> query = em.createQuery(single);
        List<CustomerBase> cList = query.getResultList();
        List<String> idList = new ArrayList<String>(cList.size());
        for(CustomerBase c : cList){
            idList.add(c.getCustomerId());
        }
        return idList;
    }
}
