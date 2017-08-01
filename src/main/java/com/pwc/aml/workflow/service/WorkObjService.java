package com.pwc.aml.workflow.service;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.workflow.dao.IFlowPointExDao;
import com.pwc.aml.workflow.dao.IWorkObjDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.workflow.dao.IFlowEventDAO;
import com.pwc.component.workflow.entity.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class WorkObjService implements IWorkObjService {


    @Autowired
    private IWorkObjDao workObjDao;

    @Autowired
    private IFlowEventDAO flowEventDAO;

    @Autowired
    private IFlowPointExDao flowPointExDao;

    @Override
    public List<FlowEvent> attach(Alerts alerts, WorkflowEx workflow) throws Exception {
        WorkObj obj = new WorkObj();
        obj.setAlerts(alerts);
        obj.setWorkObjId(UUID.randomUUID().toString());//uuid
        obj.setWorkflowEx(workflow);
        obj.setCurrentPoint(workflow.getStartPoint());
        obj.setFlowId(workflow.getFlowId());
        workObjDao.save(obj);
        return getPossibleEvents(obj);
    }

    @Override
    public List<FlowEvent> doEvent(WorkObj workObj, FlowEvent flowEvent) {

        //could do this action
        if(workObj.getCurrentPoint().getFlowPointId()==flowEvent.getFlowPointId())
        {
            FlowPointEx flowPointEx=flowPointExDao.getFlowPointEx(flowEvent.getEndpoint());
            workObj.setCurrentPoint(flowPointEx);
        }

        return null;
    }

    @Override
    public List<FlowEvent> getPossibleEvents(WorkObj workObj) {
        if (workObj.getCurrentPoint() == null) {
            return null;
        } else {
            List<FlowEvent> possibleEvents= flowEventDAO.findByPointId(workObj.getCurrentPoint().getFlowPointId());
            return possibleEvents;
        }
    }

    @Override
    public List<WorkObj> getWorkObjsByPointId(String flowPointId) throws Exception {

        return workObjDao.findWorkObjsByPointId(flowPointId);

    }

    @Override
    public void truncateTable() throws IOException {
        workObjDao.truncateTable();
    }

    @Override
    public WorkObj getWorkObjsByWorkObjId(String workObjId) {
        return null;
    }

    @Override
    public FlowEvent getFlowEventByEventId(String eventId) {
        return flowEventDAO.findByFlowEventId(eventId);
    }


}
