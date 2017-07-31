package com.pwc.aml.workflow.service;

import com.pwc.aml.workflow.dao.IRolePointDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.RolePoint;
import com.pwc.aml.workflow.entity.WorkflowEx;
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
    private IFlowPointDAO flowPointDAO;

    @Autowired
    private IFlowEventDAO flowEventDAO;

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

        Workflow workflow = workflowService.getWorkflowByFlowId(flowId);

        WorkflowEx ex = new WorkflowEx(workflow);
        List<FlowPointEx> flowPointExList = new ArrayList<>();

        // List<RolePoint> rolePoints =rolePointDao.findRolePointByFlowId(flowId);

        List<FlowPoint> flowPoints = flowPointDAO.findByFlowId(flowId);

        for (FlowPoint flowPoint : flowPoints) {
            FlowPointEx flowPointEx = new FlowPointEx();

            flowPointEx.setFlowPoint(flowPoint);
            //possible events
            List<FlowEvent> flowEvents =flowEventDAO.findByPointId(flowPoint.getFlowPointId());
            flowPointEx.setPossibleEvents(flowEvents);

            RolePoint rp= rolePointDao.findRolePointByPointId( flowPoint.getFlowPointId());
            flowPointEx.setRoleId(rp.getRoleId());
            flowPointExList.add(flowPointEx);
        }
        ex.setFlowPointsEx(flowPointExList);
        return ex;
    }


}
