package com.pwc.aml.alert.service;

import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.service.ICustomerService;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.aml.workflow.service.IWorkflowExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AlertService implements IAlertService{

    @Autowired
    private IAlertDAO alertDAO;

    @Autowired
    private IWorkflowExService workflowExService;

    @Autowired
    private IWorkObjService workObjService;

    @Autowired
    private ICustomerDAO customerDAO;

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        return alertDAO.getAllAlertsData();
    }

    @Override
    public Alerts getSingleAlert(String alertId) throws Exception {
        return alertDAO.getSingleAlert(alertId);
    }

    @Override
    public void truncateTable() throws IOException {
        alertDAO.truncateTable();
    }

    @Override
    public List<WorkObj> searchClosedAlerts(AlertSearchEntity ase) throws Exception {
        List<String> customerIdList = customerDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
        if(null == customerIdList){
            return null;
        }else{
            String defaultEndId = workflowExService.getWorkflowByDefault().getEndPoint().getFlowPointId();
            List<WorkObj> workObjList = workObjService.getWorkObjsByPointId(defaultEndId, ase);
            return workObjList;
        }
    }
}
