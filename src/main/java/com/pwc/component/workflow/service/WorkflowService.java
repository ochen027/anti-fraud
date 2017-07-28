package com.pwc.component.workflow.service;


import com.pwc.component.systemConfig.dao.IKeyValueDao;
import com.pwc.component.workflow.dao.IFlowEventDAO;
import com.pwc.component.workflow.dao.IFlowPointDAO;
import com.pwc.component.workflow.dao.IWorkflowDAO;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;
import com.pwc.component.workflow.entity.Workflow;
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

    @Autowired
    private IKeyValueDao keyValueDAO;

    private static String DEFAULT_WORKFLOW = "DEFAULT_WORKFLOW";

    @Override
    public List<Workflow> getAllWorkflow() {

        List<Workflow> workflows = workflowDAO.findAll();

        for (int i = 0; i < workflows.size(); i++) {
            workflows.get(i).setChartJson(null);
        }

        return workflows;
    }

    @Override
    public void saveOrUpdate(Workflow workflow) {
        Workflow target = workflowDAO.findByFlowId(workflow.getFlowId());
        if(null!=target){
            update(workflow);
        }else
        {
            save(workflow);
        }
    }


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


    public Workflow update(Workflow workflow) {

        Workflow result= workflowDAO.update(workflow);

        flowPointDAO.deleteByFlowId(workflow.getFlowId());

        flowEventDAO.deleteByFlowId(workflow.getFlowId());


        for (FlowPoint fp : workflow.getFlowPoints()) {
            flowPointDAO.save(fp);
        }

        for (FlowEvent fe : workflow.getFlowEvents()) {
            flowEventDAO.save(fe);
        }


        return result;
    }

    @Override
    public Workflow getWorkflowByFlowId(String FlowId) {
        Workflow workflow = workflowDAO.findByFlowId(FlowId);
        workflow.setFlowPoints(flowPointDAO.findByFlowId(FlowId));
        workflow.setFlowEvents(flowEventDAO.findByFlowId(FlowId));
        return workflow;
    }

    @Override
    public void setDefaultWorkflowId(String id, String userName) {
        keyValueDAO.put(DEFAULT_WORKFLOW, id, userName);
    }

    @Override
    public String getDefaultWorkflowId() {
        return keyValueDAO.get(DEFAULT_WORKFLOW);
    }
}
