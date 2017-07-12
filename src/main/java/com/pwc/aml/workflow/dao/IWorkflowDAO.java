package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.Workflow;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.util.List;

public interface IWorkflowDAO {

    List<Workflow> findAll();

    void save(Workflow workflow);

    void delete(String flowId);

    Workflow update(Workflow workflow);

    Workflow findByFlowId(String flowId);

}
