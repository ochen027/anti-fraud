package com.pwc.aml.workflow.service;


import com.pwc.aml.workflow.dao.IFlowEventDAO;
import com.pwc.aml.workflow.dao.IFlowPointDAO;
import com.pwc.aml.workflow.dao.IWorkflowDAO;
import com.pwc.aml.workflow.entity.FlowEvent;
import com.pwc.aml.workflow.entity.FlowPoint;
import com.pwc.aml.workflow.entity.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkflowService implements IWorkflowService {

    @Autowired
    private IWorkflowDAO workflowDAO;

    @Autowired
    private IFlowPointDAO flowPointDAO;

    @Autowired
    private IFlowEventDAO flowEventDAO;


    @Override
    public List<Workflow> getAllWorkflow() {

        List<Workflow> workflows = workflowDAO.findAll();

        for (int i = 0; i < workflows.size(); i++) {
            workflows.get(i).setChartJson(null);
        }

        return workflows;
    }


    /***
     * todo: save flowpoint and event
     * @param workflow
     */
    @Override
    public void save(Workflow workflow) {

        workflowDAO.save(workflow);

        for (FlowPoint fp : workflow.getFlowPoints()) {
            flowPointDAO.save(fp);
        }

        for (FlowEvent fe : workflow.getFlowEvents()) {
            flowEventDAO.save(fe);
        }

    }

    @Override
    public void delete(Workflow workflow) {
        workflowDAO.delete(workflow.getFlowId());
    }

    @Override
    public Workflow update(Workflow workflow) {
        return workflowDAO.update(workflow);
    }

    @Override
    public Workflow getWorkflowByFlowId(String FlowId) {

        Workflow workflow = workflowDAO.findByFlowId(FlowId);

        workflow.setFlowPoints(flowPointDAO.findByFlowId(FlowId));

        workflow.setFlowEvents(flowEventDAO.findByFlowId(FlowId));

        return workflow;
    }
}
