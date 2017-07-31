package com.pwc.aml.workflow.service;


import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.workflow.entity.FlowEvent;

import java.io.IOException;
import java.util.List;

public interface IWorkObjService {

    List<FlowEvent> attach(Alerts alerts, WorkflowEx workflow) throws Exception;

    List<FlowEvent> doEvent(WorkObj workObj, FlowEvent flowEvent);

    List<FlowEvent> getPossibleEvents(WorkObj workObj);

    List<WorkObj> getWorkObjsByPointId(String flowPointId) throws Exception;

     void truncateTable() throws IOException;

    WorkObj getWorkObjsByWorkObjId(String workObjId);

    FlowEvent getFlowEventByEventId(String eventId);
}
