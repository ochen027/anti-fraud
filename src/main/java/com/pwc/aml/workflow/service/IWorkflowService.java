package com.pwc.aml.workflow.service;


import com.pwc.aml.workflow.entity.Workflow;

import java.util.List;

public interface IWorkflowService {


    List<Workflow> getAllworkflow();

    void save(Workflow workflow);

    void deleteOne(Workflow workflow);

    Workflow update(Workflow workflow);

}
