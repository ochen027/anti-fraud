package com.pwc.aml.workflow.dao;

import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkObjSchema;
import com.pwc.common.base.dao.HadoopBaseDao;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.assign.entity.AssignSchema;
import com.pwc.component.workflow.entity.FlowEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class WorkObjDao extends HadoopBaseDao implements IWorkObjDao {



    @Autowired
    private IAlertDAO alertDAO;

    @Autowired
    private IFlowPointExDao flowPointExDao;

    public WorkObjDao(){
        this.tableKey = "aml:workObj";
    }

    @Override
    public void saveOrUpdate(WorkObj workObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        initial(workObj.getWorkObjId());
        saveColumn(WorkObjSchema.workObjectId, workObj.getWorkObjId());
        saveColumn(WorkObjSchema.flowId, workObj.getFlowId());
        saveColumn(WorkObjSchema.currentPointId, workObj.getCurrentPoint().getFlowPointId());
        saveColumn(WorkObjSchema.historyEvents, mapper.writeValueAsString(workObj.getHistoryEvents()));
        saveColumn(WorkObjSchema.roleId, "" + workObj.getCurrentPoint().getRoleId());
        saveColumn(WorkObjSchema.alertId, workObj.getAlerts().getAlertId());
        saveColumn(WorkObjSchema.isSAR, workObj.isSAR() ? "true" : "false");
        saveColumn(WorkObjSchema.createdBy, workObj.getCreatedBy());
        saveColumn(WorkObjSchema.createdDate, workObj.getCreationDate().getTime());
        saveColumn(WorkObjSchema.updateBy, workObj.getLastUpdatedBy());
        saveColumn(WorkObjSchema.updateDate, workObj.getLastUpdateDate().getTime());
        saveColumn(WorkObjSchema.isActive, workObj.isStatus() ? "true" : "false");
        saveColumn(WorkObjSchema.customerId, workObj.getAlerts().getCustomerId());
        saveColumn(WorkObjSchema.totalAmt, Double.valueOf(workObj.getAlerts().getTotalAmt()));
        saveColumn(WorkObjSchema.transIdArray, this.generateTransIds(workObj.getAlerts().getTransList()));
        saveColumn(WorkObjSchema.accountId, workObj.getAlerts().getAccountId());
        saveColumn(Constants.COLUMN_SCENARIO_ID, workObj.getAlerts().getScenarioId());
    }

    @Override
    public WorkObj findWorkObjByWorkObjId(String workObjId) throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                Bytes.toBytes(WorkObjSchema.workObjectId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(workObjId)));
        scan.setFilter(filterList);
        ResultScanner rsscan = table.getScanner(scan);
        List<WorkObj> tList = new ArrayList<WorkObj>();
        for (Result r : rsscan) {
            WorkObj t = this.CellToWorkObj(r.rawCells());
            tList.add(t);
        }
        rsscan.close();

        if (tList.size() == 1)
            return tList.get(0);
        else
            return null;
    }

    @Override
    public List<WorkObj> findWorkObjsByPointId(String flowPointId) throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(WorkObjSchema.currentPointId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(flowPointId)));
        scan.setFilter(filterList);
        ResultScanner rsscan = table.getScanner(scan);
        List<WorkObj> tList = new ArrayList<WorkObj>();
        for (Result r : rsscan) {
            WorkObj t = this.CellToWorkObj(r.rawCells());
            tList.add(t);
        }
        rsscan.close();
        return tList;
    }

    @Override
    public List<WorkObj> searchAlertWorkObject(String flowPointId, AlertSearchEntity ase) throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

        //Filter for Closed Alert if flowPointId is not Null
        if(StringUtils.isNotEmpty(flowPointId)){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                    Bytes.toBytes(WorkObjSchema.currentPointId),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(flowPointId)));
        }

        //Filter Alert Id
        if (StringUtils.isNotEmpty(ase.getAlertId())) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_ALERT_ID),
                    CompareFilter.CompareOp.EQUAL, new RegexStringComparator("[.]*" + ase.getAlertId() + "[.]*")));
        }

        //Filter Account Id
        if (StringUtils.isNotEmpty(ase.getAccountId())) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_ACCOUNT_ID),
                    CompareFilter.CompareOp.EQUAL, new RegexStringComparator("[.]*" + ase.getAccountId() + "[.]*")));
        }


        //Filter Total Amount Value
        if (null != ase.getTotalAmt()) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_TOTAL_AMOUNT),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(ase.getTotalAmt()))));
        }

        //Filter Current Point
        if (null != ase.getCurrentPointId()) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_CURRENT_POINT_ID),
                    CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(ase.getCurrentPointId()))));
        }

        //Filter Scenario
        if (null != ase.getScenarioId()) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_SCENARIO_ID),
                    CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(ase.getScenarioId()))));
        }

        //Filter Alert Created From Date to Date
        if (null != ase.getCreatedFromDate() && null != ase.getCreatedToDate()) {
            LocalDate lDateFrom = FormatUtils.DateToLocalDate(ase.getCreatedFromDate()).minusDays(1L);
            Date fromDate = FormatUtils.LocalDateToDate(lDateFrom);
            LocalDate lDateTo = FormatUtils.DateToLocalDate(ase.getCreatedToDate()).plusDays(1L);
            Date toDate = FormatUtils.LocalDateToDate(lDateTo);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(fromDate.getTime()))));
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes(toDate.getTime()))));
        }

        if (null != ase.getCreatedFromDate() && null == ase.getCreatedToDate()) {
            LocalDate lDateFrom = FormatUtils.DateToLocalDate(ase.getCreatedFromDate()).minusDays(1L);
            Date fromDate = FormatUtils.LocalDateToDate(lDateFrom);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(fromDate.getTime())));
        }

        if (null == ase.getCreatedFromDate() && null != ase.getCreatedToDate()) {
            LocalDate lDateTo = FormatUtils.DateToLocalDate(ase.getCreatedToDate()).plusDays(1L);
            Date toDate = FormatUtils.LocalDateToDate(lDateTo);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(toDate.getTime())));
        }

        //Filter Alert Closed Date
        if (null != ase.getClosedFromDate() && null != ase.getClosedToDate()) {
            LocalDate lDateFrom = FormatUtils.DateToLocalDate(ase.getClosedFromDate()).minusDays(1L);
            Date fromDate = FormatUtils.LocalDateToDate(lDateFrom);

            LocalDate lDateTo = FormatUtils.DateToLocalDate(ase.getClosedToDate()).plusDays(1L);
            Date toDate = FormatUtils.LocalDateToDate(lDateTo);


            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(fromDate.getTime())));
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(toDate.getTime())));
        }

        if (null != ase.getClosedFromDate() && null == ase.getClosedToDate()) {

            LocalDate lDateFrom = FormatUtils.DateToLocalDate(ase.getClosedFromDate()).minusDays(1L);
            Date fromDate = FormatUtils.LocalDateToDate(lDateFrom);

            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(fromDate.getTime())));
        }

        if (null == ase.getClosedFromDate() && null != ase.getClosedToDate()) {
            LocalDate lDateTo = FormatUtils.DateToLocalDate(ase.getClosedToDate()).plusDays(1L);
            Date toDate = FormatUtils.LocalDateToDate(lDateTo);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(toDate.getTime())));
        }

        //Filter Alert Closed By
        if (StringUtils.isNotEmpty(ase.getClosedBy())) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_BY),
                    CompareFilter.CompareOp.EQUAL, new RegexStringComparator(
                    "[.]*" + ase.getClosedBy() + "[.]*")));
        }

        //Filter Alert Transactions Array
        if(StringUtils.isNotEmpty(ase.getTransIdArray())){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_TRANS_ID_ARRAY),
                    CompareFilter.CompareOp.EQUAL, new RegexStringComparator(
                    "[.]*" + ase.getTransIdArray() + "[.]*")));
        }


        if (StringUtils.isNotEmpty(ase.getSuspiciousLevel())) {
            //TODO
        }


        if (null != ase.getCustomerIdList()) {
            List<WorkObj> woList = new ArrayList<WorkObj>();
            for (String cId : ase.getCustomerIdList()) {

                filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                        Bytes.toBytes(WorkObjSchema.customerId),
                        CompareFilter.CompareOp.EQUAL, Bytes.toBytes(cId)));

                scan.setFilter(filterList);
                ResultScanner rsscan = table.getScanner(scan);
                List<WorkObj> wList = new ArrayList<WorkObj>();
                for (Result r : rsscan) {
                    WorkObj t = this.CellToWorkObj(r.rawCells());
                    wList.add(t);
                }
                rsscan.close();
                woList.addAll(wList);
            }
            return woList;
        } else if (null == ase.getCustomerIdList() && (StringUtils.isNotEmpty(ase.getCustomerId()) || StringUtils.isNotEmpty(ase.getCustomerName()))) {
            return null;
        } else {
            scan.setFilter(filterList);
            ResultScanner rsscan = table.getScanner(scan);
            List<WorkObj> wList = new ArrayList<WorkObj>();
            for (Result r : rsscan) {
                WorkObj t = this.CellToWorkObj(r.rawCells());
                wList.add(t);
            }
            rsscan.close();
            return wList;
        }
    }

    @Override
    public Integer getClosedAlertByDateCount(String endPointId, String businessDate) throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(WorkObjSchema.currentPointId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(endPointId)));
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(FormatUtils.StringToDateNoDash(businessDate).getTime())));
        scan.setFilter(filterList);
        return CountResultList(table.getScanner(scan));
    }

    @Override
    public Integer getSARAlertByDateCount(String businessDate) throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(WorkObjSchema.isSAR),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes("true")));
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(FormatUtils.StringToDateNoDash(businessDate).getTime())));
        scan.setFilter(filterList);
        return CountResultList(table.getScanner(scan));
    }

    @Override
    public Integer getAlertByDateCount(String businessDate) throws Exception{
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(FormatUtils.StringToDateNoDash(businessDate).getTime())));
        scan.setFilter(filterList);
        return CountResultList(table.getScanner(scan));
    }

    private Integer CountResultList(ResultScanner rsscan){
        int i = 0;
        for(Result r : rsscan){
            i++;
        }
        return i;
    }

    private WorkObj CellToWorkObj(Cell[] cells) throws Exception {
        WorkObj o = new WorkObj();
        ObjectMapper mapper = new ObjectMapper();
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch (key) {
                case WorkObjSchema.workObjectId:
                    o.setWorkObjId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case WorkObjSchema.flowId:
                    String flowId = Bytes.toString(CellUtil.cloneValue(c));
                    o.setFlowId(flowId);
                    break;
                case WorkObjSchema.currentPointId:
                    String currentPointId = Bytes.toString(CellUtil.cloneValue(c));
                    FlowPointEx flowPointEx = flowPointExDao.getFlowPointEx(currentPointId);
                    o.setCurrentPoint(flowPointEx);
                    break;
                case WorkObjSchema.historyEvents:
                    String json = Bytes.toString(CellUtil.cloneValue(c));
                    List<FlowEvent> flowEvents = (List<FlowEvent>) mapper.readValue(json, List.class);
                    o.setHistoryEvents(flowEvents);
                    break;
                case WorkObjSchema.alertId:
                    String alertId = Bytes.toString(CellUtil.cloneValue(c));
                    Alerts alerts = alertDAO.getSingleAlert(alertId);
                    Long days = ChronoUnit.DAYS.between(FormatUtils.StringToLocalDateNoDash(alerts.getCreatedDate()), LocalDate.now());
                    alerts.setDays(String.valueOf(days));
                    o.setAlerts(alerts);
                    break;
                case WorkObjSchema.isSAR:
                    String isSAR = Bytes.toString(CellUtil.cloneValue(c));
                    o.setSAR(isSAR.equals("true"));
                    break;
                case WorkObjSchema.createdBy:
                    String createdBy = Bytes.toString(CellUtil.cloneValue(c));
                    o.setCreatedBy(createdBy);
                    break;
                case WorkObjSchema.createdDate:
                    Long createdDateTime = Bytes.toLong(CellUtil.cloneValue(c));
                    o.setCreationDate(new Date(createdDateTime));
                    break;
                case WorkObjSchema.updateBy:
                    String updateBy = Bytes.toString(CellUtil.cloneValue(c));
                    o.setLastUpdatedBy(updateBy);
                    break;
                case WorkObjSchema.updateDate:
                    Long updateDateTime = Bytes.toLong(CellUtil.cloneValue(c));
                    o.setLastUpdateDate(new Date(updateDateTime));
                    break;
                case WorkObjSchema.isActive:
                    String isActive = Bytes.toString(CellUtil.cloneValue(c));
                    o.setStatus(isActive.equals("true"));
                    break;
                case WorkObjSchema.customerId:
                    o.setCustomerId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case WorkObjSchema.totalAmt:
                    o.setTotalAmt(Bytes.toDouble(CellUtil.cloneValue(c)));
                    break;
                case WorkObjSchema.accountId:
                    o.setAccountId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case WorkObjSchema.transIdArray:
                    o.setTransIdArray(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case Constants.COLUMN_SCENARIO_ID:
                    o.setScenarioId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
            }
        }

        return o;
    }

    private String generateTransIds(List<Transactions> tList){
        StringBuffer sb = new StringBuffer();
        for(Transactions t : tList){
            sb.append(t.getTransId()+Constants.COMMA);
        }
        return sb.substring(0, sb.length()-1).toString();
    }

}
