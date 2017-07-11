package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.Workflow;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.util.List;

public interface IWorkflowDAO {

    List<Workflow> getAllworkflow();

    void save(Workflow workflow);

    void deleteOne(Workflow workflow);

    Workflow update(Workflow workflow);
}
