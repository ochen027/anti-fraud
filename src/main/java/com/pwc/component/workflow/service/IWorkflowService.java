package com.pwc.component.workflow.service;


import com.pwc.component.workflow.entity.Workflow;

import java.util.List;

public interface IWorkflowService {

    /**
     * list workflow basic information
     * @return
     */
    List<Workflow> getAllWorkflow();

    void saveOrUpdate(Workflow workflow);

    void delete(Workflow workflow);

    Workflow getWorkflowByFlowId(String FlowId);



}
