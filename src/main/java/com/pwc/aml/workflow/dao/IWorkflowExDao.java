package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.WorkflowEx;

public interface IWorkflowExDao {

    WorkflowEx getWorkflowByFlowId(String flowId);
}
