package com.pwc.aml.workflow.service;


import com.pwc.aml.workflow.dao.IWorkflowDAO;
import com.pwc.aml.workflow.entity.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class WorkflowService implements IWorkflowService {

    @Autowired
    private IWorkflowDAO workflowDAO;

    @Override
    public List<Workflow> getAllworkflow() {
        return workflowDAO.getAllworkflow();
    }

    @Override
    public void save(Workflow workflow) {
        workflowDAO.save(workflow);
    }

    @Override
    public void deleteOne(Workflow workflow) {
        workflowDAO.deleteOne(workflow);
    }

    @Override
    public Workflow update(Workflow workflow) {
        return workflowDAO.update(workflow);
    }
}
