package com.pwc.aml.watchlist.dao;


import com.pwc.aml.watchlist.entity.WatchList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by whuang072 on 7/21/2017.
 */

@Transactional
@Repository
public class WatchListDao implements IWatchListDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public WatchList save(WatchList watchList) {
        em.persist(watchList);
        return watchList;
    }

    @Override
    public WatchList update(WatchList watchList) {
        return em.merge(watchList);
    }

    @Override
    public void deleteById(int id) {
        WatchList wl = em.find(WatchList.class, id);
        wl.setStatus(false);
        em.merge(wl);
        em.flush();
    }

    @Override
    public void delete(WatchList wl) {
        WatchList target = em.find(WatchList.class, wl.getId());
        em.remove(target);
    }

    @Override
    public List<WatchList> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WatchList> cq = cb.createQuery(WatchList.class);
        Root<WatchList> rootEntry = cq.from(WatchList.class);
        CriteriaQuery<WatchList> all = cq.select(rootEntry);
        TypedQuery<WatchList> query = em.createQuery(all);
        return query.getResultList();
    }

    @Override
    public WatchList findById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WatchList> cq = cb.createQuery(WatchList.class);
        Root<WatchList> rootEntry = cq.from(WatchList.class);
        Predicate predicate = cb.equal(rootEntry.get("id"), id);
        CriteriaQuery<WatchList> single = cq.select(rootEntry).where(predicate);
        TypedQuery<WatchList> query = em.createQuery(single);
        List<WatchList> rcs = query.getResultList();
        if (rcs.isEmpty()) {
            return null;
        } else {
            return rcs.get(0);
        }

    }

    @Override
    public void removeAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<WatchList> cq = cb.createCriteriaDelete(WatchList.class);
        Root<WatchList> root = cq.from(WatchList.class);
        em.createQuery(cq).executeUpdate();

    }
}

