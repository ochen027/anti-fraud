package com.pwc.aml.alert.service;

import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.service.ICustomerService;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.workflow.dao.IWorkflowExDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.component.systemConfig.dao.IKeyValueDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AlertService implements IAlertService{

    @Autowired
    IAlertDAO alertDAO;

    @Autowired
    IWorkflowExService workflowExService;

    @Autowired
    IWorkObjService workObjService;

    @Autowired
    ICustomerBaseDao customerBaseDAO;

    @Autowired
    IKeyValueDao keyValueDAO;

    @Autowired
    IWorkflowExDao workflowExDao;


    static String DEFAULT_WORKFLOW = "DEFAULT_WORKFLOW";

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
        List<String> customerIdList = null;
        if(StringUtils.isNotEmpty(ase.getCustomerId()) || StringUtils.isNotEmpty(ase.getCustomerId())){
            customerIdList = customerBaseDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
            if(null == customerIdList){
                return null;
            }
            ase.setAllCustomer(false);
            ase.setCustomerIdList(customerIdList);
        }else{
            ase.setAllCustomer(true);
        }

        String defaultEndId = workflowExService.getWorkflowByDefault().getEndPoint().getFlowPointId();
        List<WorkObj> workObjList = workObjService.getWorkObjsByPointId(defaultEndId, ase);
        return workObjList;

    }

    @Override
    public void createAlertByManually(List<Transactions> tList, Scenario scenario, String userName) throws Exception {
        String alertId = alertDAO.createByManually(tList, scenario, userName);
        workObjService.attach(this.getSingleAlert(alertId),workflowExDao.getWorkflowByFlowId(keyValueDAO.get(DEFAULT_WORKFLOW)),userName);
    }
}
