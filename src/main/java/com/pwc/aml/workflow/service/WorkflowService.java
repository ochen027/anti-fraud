package com.pwc.aml.workflow.service;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.workflow.dao.IWorkflowDAO;
import com.pwc.aml.workflow.entity.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WorkflowService implements IWorkflowService {


    @Override
    public List<Workflow> getAllworkflow() {
        return null;
    }
}
