package com.pwc.aml.workflow.service;


import com.pwc.aml.workflow.entity.Workflow;

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
