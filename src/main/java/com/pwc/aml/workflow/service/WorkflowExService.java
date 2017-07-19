package com.pwc.aml.workflow.service;

import com.pwc.aml.workflow.dao.IRolePointDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.RolePoint;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.workflow.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkflowExService implements IWorkflowExService{

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private IRolePointDao rolePointDao;


    @Override
    public void saveOrUpdate(WorkflowEx workflow) {
        //save workflow basic information
        workflowService.saveOrUpdate(workflow.getWorkflow());

        //save role points
        List<FlowPointEx> flowpointsEx= workflow.getFlowPointsEx();
        for(FlowPointEx item : flowpointsEx){
            RolePoint rolePoint=new RolePoint(item.getRoleId(),item.getFlowPointId());
            rolePointDao.save(rolePoint);
        }

    }
}
