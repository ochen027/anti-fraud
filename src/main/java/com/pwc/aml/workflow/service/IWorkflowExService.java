package com.pwc.aml.workflow.service;

import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.workflow.entity.Workflow;

/**
 * Created by whuang072 on 7/19/2017.
 */
public interface IWorkflowExService {

    void saveOrUpdate(WorkflowEx workflow);

    WorkflowEx getWorkflowByFlowId(String flowId);
}
