package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.RolePoint;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;
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
public class RolePointDao implements IRolePointDao  {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(RolePoint rolePoint) {
        em.persist(rolePoint);
    }

    @Override
    public RolePoint findRolePointByPointId(String flowPointId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RolePoint> cq = cb.createQuery(RolePoint.class);
        Root<RolePoint> rootEntry = cq.from(RolePoint.class);
        Predicate predicate = cb.equal(rootEntry.get("flowPointId"), flowPointId);
        CriteriaQuery<RolePoint> single = cq.select(rootEntry).where(predicate);
        TypedQuery<RolePoint> query = em.createQuery(single);
        List<RolePoint> RolePoints = query.getResultList();

        if (RolePoints.isEmpty()) {
            return null;
        } else {
            return RolePoints.get(0);
        }
    }

    @Override
    public List<RolePoint> findRolePointByFlowId(String flowId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RolePoint> cq = cb.createQuery(RolePoint.class);
        Root<RolePoint> rootEntry = cq.from(RolePoint.class);

        CriteriaQuery<FlowPoint> cq2 = cb.createQuery(FlowPoint.class);
        Root<FlowPoint> rootEntry2 = cq2.from(FlowPoint.class);

        Predicate predicate = cb.equal(rootEntry2.get("flowId"), flowId);
        Predicate predicate2 = cb.equal(rootEntry.get("flowPointId"), rootEntry2.get("flowPointId"));
        Predicate criteria = cb.conjunction();
        criteria = cb.and(criteria, predicate);
        criteria = cb.and(criteria, predicate2);

        CriteriaQuery<RolePoint> single = cq.select(rootEntry).where(criteria);
        TypedQuery<RolePoint> query = em.createQuery(single);
        List<RolePoint> RolePoints = query.getResultList();
        return RolePoints;
    }


}
