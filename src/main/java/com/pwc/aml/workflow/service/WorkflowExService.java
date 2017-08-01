package com.pwc.aml.workflow.service;

import com.pwc.aml.workflow.dao.IRolePointDao;
import com.pwc.aml.workflow.dao.IWorkflowExDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.RolePoint;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.systemConfig.dao.IKeyValueDao;
import com.pwc.component.workflow.dao.IFlowEventDAO;
import com.pwc.component.workflow.dao.IFlowPointDAO;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;
import com.pwc.component.workflow.entity.Workflow;
import com.pwc.component.workflow.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WorkflowExService implements IWorkflowExService {

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private IRolePointDao rolePointDao;

    @Autowired
    private IKeyValueDao keyValueDAO;

    private static String DEFAULT_WORKFLOW = "DEFAULT_WORKFLOW";


    @Autowired
    private IWorkflowExDao workflowExDao;

    @Override
    public void saveOrUpdate(WorkflowEx workflow) {
        //save workflow basic information
        workflowService.saveOrUpdate(workflow.getWorkflow());
        //save role points
        List<FlowPointEx> flowpointsEx = workflow.getFlowPointsEx();
        for (FlowPointEx item : flowpointsEx) {
            RolePoint rolePoint = new RolePoint(item.getRoleId(), item.getFlowPointId());
            rolePointDao.save(rolePoint);
        }
    }

    @Override
    public WorkflowEx getWorkflowByFlowId(String flowId) {
        return workflowExDao.getWorkflowByFlowId(flowId);
    }

    @Override
    public void setDefaultWorkflowId(String id, String userName) {
        keyValueDAO.put(DEFAULT_WORKFLOW, id, userName);
    }

    @Override
    public String getDefaultWorkflowId() {
        return keyValueDAO.get(DEFAULT_WORKFLOW);
    }

    @Override
    public WorkflowEx getWorkflowByDefault() {
        String defaultId = getDefaultWorkflowId();
        return  getWorkflowByFlowId(defaultId);
    }



}
