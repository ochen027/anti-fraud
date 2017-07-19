package com.pwc.aml.workflow.service;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.workflow.entity.FlowEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkObjService implements IWorkObjService {


    @Override
    public List<FlowEvent> attach(Alerts alerts, WorkflowEx workflow) {
        return null;
    }

    @Override
    public void doEvent(WorkObj workObj, FlowEvent flowEvent) {

    }

    @Override
    public List<FlowEvent> getPossibleEvents(WorkObj workObj) {
        return null;
    }
}
