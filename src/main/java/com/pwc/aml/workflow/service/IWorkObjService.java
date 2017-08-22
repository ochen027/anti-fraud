package com.pwc.aml.workflow.service;


import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.component.assign.entity.Assign;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.workflow.entity.FlowEvent;

import java.io.IOException;
import java.util.List;

public interface IWorkObjService {

    List<FlowEvent> attach(Alerts alerts, WorkflowEx workflow,String userName) throws Exception;

    List<FlowEvent> doEvent(WorkObj workObj, FlowEvent flowEvent,String userName) throws Exception;

    List<FlowEvent> getPossibleEvents(WorkObj workObj);

    List<WorkObj> getWorkObjsByPointId(String flowPointId, AlertSearchEntity ase) throws Exception;

     void truncateTable() throws IOException;

    WorkObj getWorkObjsByWorkObjId(String workObjId) throws Exception;

    FlowEvent getFlowEventByEventId(String eventId);

    List<WorkObj> getWorkObjsByAssigns(List<Assign> assigns) throws Exception;

    void updateWorkObj(WorkObj workObj,String userName) throws Exception;

}
