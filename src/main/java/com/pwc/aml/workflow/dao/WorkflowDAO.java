package com.pwc.aml.workflow.dao;

import com.pwc.aml.workflow.entity.Workflow;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Transactional
@Repository
public class WorkflowDAO implements IWorkflowDAO {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Workflow> getAllworkflow() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Workflow> cq = cb.createQuery(Workflow.class);
        Root<Workflow> rootEntry = cq.from(Workflow.class);
        CriteriaQuery<Workflow> all = cq.select(rootEntry);
        TypedQuery<Workflow> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Workflow workflow) {
        em.persist(workflow);
    }

    @Override
    public void deleteOne(Workflow workflow) {
         Workflow wf= em.find(Workflow.class,workflow.getId());
         em.remove(wf);
    }

    @Override
    public Workflow update(Workflow workflow) {
       Workflow wf= em.merge(workflow);
        return wf;
    }


}
