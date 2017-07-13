package com.pwc.aml.workflow.dao;

import com.pwc.aml.workflow.entity.FlowEvent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class FlowEventDAO implements IFlowEventDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(FlowEvent flowEvent) {
        em.persist(flowEvent);
    }

    @Override
    public void delete(String flowEventId) {
        FlowEvent fe=findByFlowEventId( flowEventId);
        em.remove(fe);
    }


    @Override
    public FlowEvent update(FlowEvent flowEvent) {
        FlowEvent target=findByFlowEventId( flowEvent.getFlowEventId());
        flowEvent.setId(target.getId());
        return em.merge(flowEvent);
    }

    @Override
    public List<FlowEvent> findByFlowId(String flowId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FlowEvent> cq = cb.createQuery(FlowEvent.class);
        Root<FlowEvent> rootEntry = cq.from(FlowEvent.class);
        Predicate predicate = cb.equal(rootEntry.get("flowId"),flowId );
        CriteriaQuery<FlowEvent> single = cq.select(rootEntry).where(predicate);
        TypedQuery<FlowEvent> query = em.createQuery(single);
        List<FlowEvent> fes = query.getResultList();
        return fes;
    }

    @Override
    public List<FlowEvent> findByPointId(String pointId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FlowEvent> cq = cb.createQuery(FlowEvent.class);
        Root<FlowEvent> rootEntry = cq.from(FlowEvent.class);
        Predicate predicate = cb.equal(rootEntry.get("pointId"), pointId);
        CriteriaQuery<FlowEvent> single = cq.select(rootEntry).where(predicate);
        TypedQuery<FlowEvent> query = em.createQuery(single);
        List<FlowEvent> fes = query.getResultList();
        return fes;
    }

    @Override
    public FlowEvent findByFlowEventId(String flowEventId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FlowEvent> cq = cb.createQuery(FlowEvent.class);
        Root<FlowEvent> rootEntry = cq.from(FlowEvent.class);
        Predicate predicate = cb.equal(rootEntry.get("flowEventId"), flowEventId);
        CriteriaQuery<FlowEvent> single = cq.select(rootEntry).where(predicate);
        TypedQuery<FlowEvent> query = em.createQuery(single);
        FlowEvent fe = query.getSingleResult();
        return fe;
    }
}
