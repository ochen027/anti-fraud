package com.pwc.component.workflow.dao;

import com.pwc.component.workflow.entity.FlowPoint;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Transactional
@Repository
public class FlowPointDAO implements IFlowPointDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(FlowPoint flowPoint) {
        em.persist(flowPoint);
    }

    @Override
    public void delete(String flowPointId) {
        FlowPoint fp = find(flowPointId);
        em.remove(fp);
    }


    @Override
    public FlowPoint update(FlowPoint flowPoint) {
        FlowPoint target = find(flowPoint.getFlowPointId());
        flowPoint.setId(target.getId());
        return em.merge(flowPoint);
    }

    @Override
    public FlowPoint find(String flowPointId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FlowPoint> cq = cb.createQuery(FlowPoint.class);
        Root<FlowPoint> rootEntry = cq.from(FlowPoint.class);
        Predicate predicate = cb.equal(rootEntry.get("flowPointId"), flowPointId);
        CriteriaQuery<FlowPoint> single = cq.select(rootEntry).where(predicate);
        TypedQuery<FlowPoint> query = em.createQuery(single);

        List<FlowPoint> fps = query.getResultList();
        if (fps.isEmpty()) {
            return null;
        } else {
            return fps.get(0);
        }

    }

    @Override
    public List<FlowPoint> findByFlowId(String flowId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FlowPoint> cq = cb.createQuery(FlowPoint.class);
        Root<FlowPoint> rootEntry = cq.from(FlowPoint.class);
        Predicate predicate = cb.equal(rootEntry.get("flowId"), flowId);
        CriteriaQuery<FlowPoint> all = cq.select(rootEntry).where(predicate);
        TypedQuery<FlowPoint> query = em.createQuery(all);
        List<FlowPoint> fps = query.getResultList();
        return fps;
    }

    @Override
    public void deleteByFlowId(String flowId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<FlowPoint> cq = cb.createCriteriaDelete(FlowPoint.class);
        Root<FlowPoint> rootEntry = cq.from(FlowPoint.class);
        Predicate predicate = cb.equal(rootEntry.get("flowId"),flowId);
        cq.where(predicate);
        em.createQuery(cq).executeUpdate();
    }
}
