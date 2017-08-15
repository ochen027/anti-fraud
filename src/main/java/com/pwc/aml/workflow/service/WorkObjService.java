package com.pwc.aml.workflow.service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.assign.entity.Assign;
import com.pwc.aml.workflow.dao.IFlowPointExDao;
import com.pwc.aml.workflow.dao.IWorkObjDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.workflow.dao.IFlowEventDAO;
import com.pwc.component.workflow.entity.FlowEvent;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private ICustomerDAO customerDAO;

    @Override
    public List<FlowEvent> attach(Alerts alerts, WorkflowEx workflow,Users users) throws Exception {
        WorkObj obj = new WorkObj();
        obj.setAlerts(alerts);
        obj.setWorkObjId(UUID.randomUUID().toString());//uuid
        obj.setWorkflowEx(workflow);
        obj.setCurrentPoint(workflow.getStartPoint());
        obj.setFlowId(workflow.getFlowId());
        obj.setCreatedBy(users.getUserName());
        obj.setCreationDate(FormatUtils.getCurrentDay());
        obj.setLastUpdatedBy(users.getUserName());
        obj.setLastUpdateDate(FormatUtils.getCurrentDay());
        obj.setStatus(true);
        workObjDao.saveOrUpdate(obj);
        return getPossibleEvents(obj);
    }

    @Override
    public List<FlowEvent> doEvent(WorkObj workObj, FlowEvent flowEvent,Users users) throws Exception {

        //could do this action
        if(workObj.getCurrentPoint().getFlowPointId().equalsIgnoreCase(flowEvent.getFlowPointId()))
        {
            FlowPointEx flowPointEx=flowPointExDao.getFlowPointEx(flowEvent.getEndpoint());
            workObj.setCurrentPoint(flowPointEx);
            List<FlowEvent> oldEvents= workObj.getHistoryEvents();
            if(oldEvents==null)
            {
                oldEvents=new ArrayList<>();
            }
            oldEvents.add(flowEvent);
            workObj.setHistoryEvents(oldEvents);
            workObj.setLastUpdatedBy(users.getUserName());
            workObj.setLastUpdateDate(FormatUtils.getCurrentDay());
            workObjDao.saveOrUpdate(workObj);
        }

        return getPossibleEvents(workObj);
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
    public List<WorkObj> getWorkObjsByPointId(String flowPointId, AlertSearchEntity ase) throws Exception {
        if(null == ase){
           return workObjDao.findWorkObjsByPointId(flowPointId);
        }else{
            if(StringUtils.isNotEmpty(ase.getCustomerId()) || StringUtils.isNotEmpty(ase.getCustomerName())){
                List<String> customerIdList = customerDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
                if(null == customerIdList){
                    ase.setCustomerIdList(null);
                }else{
                    ase.setCustomerIdList(customerIdList);
                }
            }
            return workObjDao.searchClosedAlertWorkObject(flowPointId, ase);
        }
    }

    @Override
    public void truncateTable() throws IOException {
        workObjDao.truncateTable();
    }

    @Override
    public WorkObj getWorkObjsByWorkObjId(String workObjId) throws Exception {
        return workObjDao.findWorkObjByWorkObjId(workObjId);
    }

    @Override
    public FlowEvent getFlowEventByEventId(String eventId) {
        return flowEventDAO.findByFlowEventId(eventId);
    }

    @Override
    public List<WorkObj> getWorkObjsByAssigns(List<Assign> assigns) throws Exception {
        List<WorkObj> workObjs=new ArrayList<>();
        for(Assign as:assigns){
            WorkObj o=getWorkObjsByWorkObjId(as.getObjId());
            workObjs.add(o);
        }
        return workObjs;
    }

}
