package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.RolePoint;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class RolePointDao implements IRolePointDao  {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(RolePoint rolePoint) {
        em.persist(rolePoint);
    }


}
