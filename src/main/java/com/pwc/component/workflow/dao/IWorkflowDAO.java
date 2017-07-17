package com.pwc.component.workflow.dao;


import com.pwc.component.workflow.entity.Workflow;

import java.util.List;

public interface IWorkflowDAO {

    List<Workflow> findAll();

    void save(Workflow workflow);

    void delete(String flowId);

    Workflow update(Workflow workflow);

    Workflow findByFlowId(String flowId);

}
