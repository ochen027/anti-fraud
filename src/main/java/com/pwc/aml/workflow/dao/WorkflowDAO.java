package com.pwc.aml.workflow.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@Repository
public class WorkflowDAO implements IWorkflowDAO {
    @PersistenceContext
    private EntityManager entityManager;


}
