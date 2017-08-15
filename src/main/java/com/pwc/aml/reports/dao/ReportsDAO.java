package com.pwc.aml.reports.dao;

import com.pwc.aml.reports.entity.Reports;
import com.pwc.aml.transation.dao.ITransactionDAO;
import com.pwc.aml.workflow.dao.IWorkObjDao;
import com.pwc.aml.workflow.service.IWorkflowExService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportsDAO implements IReportsDAO {

    @Autowired
    private ITransactionDAO transactionDAO;

    @Autowired
    private IWorkObjDao workObjDAO;

    @Autowired
    private IWorkflowExService workflowExService;

    @Override
    public Reports searchReport(String bDate) throws Exception{
        if(bDate.indexOf("-")>0){
            bDate = StringUtils.replace(bDate, "-", "");
        }
        Reports r = new Reports();
        r.setTransCount(transactionDAO.getTransByDateCount(bDate));
        int alertsCount = workObjDAO.getAlertByDateCount(bDate);
        r.setAlertCount(alertsCount);
        r.setAlertSuppressCount(0);
        int alertsClosedCount = workObjDAO.getClosedAlertByDateCount(
                workflowExService.getWorkflowByDefault().getEndPoint().getFlowPointId(),
                bDate);
        r.setAlertClosedCount(alertsClosedCount);
        r.setAlertSARCount(workObjDAO.getSARAlertByDateCount(bDate));
        r.setAlertActiveCount(alertsCount - alertsClosedCount);
        r.setDate(bDate);
        return r;
    }

}
