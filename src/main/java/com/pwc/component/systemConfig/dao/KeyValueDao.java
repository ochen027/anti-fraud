package com.pwc.component.systemConfig.dao;


import com.pwc.component.systemConfig.entity.KeyValueConfig;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


@Transactional
@Repository
public class KeyValueDao implements IKeyValueDao {


    @PersistenceContext
    private EntityManager em;


    private KeyValueConfig findbyKey(String key){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<KeyValueConfig> cq = cb.createQuery(KeyValueConfig.class);
        Root<KeyValueConfig> rootEntry = cq.from(KeyValueConfig.class);
        Predicate predicate = cb.equal(rootEntry.get("key"), key);
        CriteriaQuery<KeyValueConfig> single = cq.select(rootEntry).where(predicate);
        TypedQuery<KeyValueConfig> query = em.createQuery(single);
        List<KeyValueConfig> kvs = query.getResultList();
        if (kvs.isEmpty()) {
            return null;
        } else {
            return kvs.get(0);
        }
    }

    @Override
    public String get(String key) {
        KeyValueConfig kv=findbyKey( key);
        if (kv==null) {
            return null;
        } else {
            return kv.getValue();
        }
    }

    @Override
    public void put(String key, String value, String userName) {
        KeyValueConfig target= findbyKey(key);
        KeyValueConfig kv = new KeyValueConfig(key, value);
        if (null == target) {
            kv.setCreatedBy(userName);
            kv.setCreationDate(new Date());
            em.persist(kv);
        } else {
            kv.setLastUpdatedBy(userName);
            target.setValue(value);
            em.merge(target);
        }
    }

    @Override
    public void remove(String key) {
        String targetValue = get(key);
        if (null != targetValue)
            em.remove(targetValue);
    }
}
