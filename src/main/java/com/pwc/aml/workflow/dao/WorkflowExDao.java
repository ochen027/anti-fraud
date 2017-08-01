package com.pwc.aml.workflow.dao;

import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.workflow.dao.IFlowEventDAO;
import com.pwc.component.workflow.dao.IFlowPointDAO;
import com.pwc.component.workflow.entity.FlowPoint;
import com.pwc.component.workflow.entity.Workflow;
import com.pwc.component.workflow.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class WorkflowExDao implements IWorkflowExDao {

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private IRolePointDao rolePointDao;


    @Autowired
    private IFlowPointDAO flowPointDAO;

    @Autowired
    private IFlowEventDAO flowEventDAO;

    @Autowired
    private IFlowPointExDao flowPointExDao;


    @Override
    public WorkflowEx getWorkflowByFlowId(String flowId) {
        Workflow workflow = workflowService.getWorkflowByFlowId(flowId);

        WorkflowEx ex = new WorkflowEx(workflow);
        List<FlowPointEx> flowPointExList = new ArrayList<>();

        List<FlowPoint> flowPoints = flowPointDAO.findByFlowId(flowId);

        for (FlowPoint flowPoint : flowPoints) {
            FlowPointEx flowPointEx=flowPointExDao.getFlowPointEx(flowPoint);

            flowPointExList.add(flowPointEx);
        }
        ex.setFlowPointsEx(flowPointExList);
        return ex;
    }
}
