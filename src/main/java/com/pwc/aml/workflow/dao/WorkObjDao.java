package com.pwc.aml.workflow.dao;

import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkObjSchema;
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
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class WorkObjDao implements IWorkObjDao {

    @Autowired
    private IHbaseDao hbaseDao;


    @Autowired
    private IWorkflowExDao workflowExDao;

    @Autowired
    private IAlertDAO alertDAO;

    @Autowired
    private IFlowPointExDao flowPointExDao;

    private HTable table;

    private String rowKey;

    private String tableKey = "aml:workObj";

    @Override
    public void save(WorkObj workObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        initial(workObj.getWorkObjId());
        saveColumn(WorkObjSchema.workObjectId, workObj.getWorkObjId());
        saveColumn(WorkObjSchema.flowId, workObj.getFlowId());
        saveColumn(WorkObjSchema.currentPointId, workObj.getCurrentPoint().getFlowPointId());
        saveColumn(WorkObjSchema.historyEvents, mapper.writeValueAsString(workObj.getHistoryEvents()));
        saveColumn(WorkObjSchema.roleId, "" + workObj.getCurrentPoint().getRoleId());
        saveColumn(WorkObjSchema.alertId, workObj.getAlerts().getAlertId());
        saveColumn(WorkObjSchema.createdBy, workObj.getCreatedBy());
        saveColumn(WorkObjSchema.createdDate, FormatUtils.DateToString(workObj.getCreationDate()));
        saveColumn(WorkObjSchema.updateBy, workObj.getLastUpdatedBy());
        saveColumn(WorkObjSchema.updateDate, FormatUtils.DateToString( workObj.getLastUpdateDate()));
        saveColumn(WorkObjSchema.isActive, workObj.isStatus() ? "true" : "false");
        saveColumn(WorkObjSchema.customerId, workObj.getAlerts().getCustomerId());
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
    public List<WorkObj> searchClosedAlertWorkObject(String flowPointId, AlertSearchEntity ase) throws Exception{
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(WorkObjSchema.currentPointId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(flowPointId)));

        if(StringUtils.isNotEmpty(ase.getAlertId())){
            RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator(
                    ase.getAlertId()+"\\w|\\w"+ase.getAlertId()+"\\w|\\w"+ase.getAlertId()));
            filterList.addFilter(rowFilter);
        }

        if(null != ase.getTotalAmt()){
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_TOTAL_AMOUNT),
                    CompareFilter.CompareOp.GREATER,Bytes.toBytes(ase.getTotalAmt()));
            filterList.addFilter(filter);
        }

        if(StringUtils.isNotEmpty(ase.getCreatedFromDate()) && StringUtils.isNotEmpty(ase.getCreatedToDate())){
            Filter createdStartDateFilter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL,Bytes.toBytes(ase.getCreatedFromDate()));
            Filter createdEndDateFilter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL,Bytes.toBytes(ase.getCreatedToDate()));
            filterList.addFilter(createdStartDateFilter);
            filterList.addFilter(createdEndDateFilter);
        }

        if(StringUtils.isNotEmpty(ase.getCreatedFromDate()) && StringUtils.isEmpty(ase.getCreatedToDate())){
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL,Bytes.toBytes(ase.getCreatedFromDate()));
            filterList.addFilter(filter);
        }

        if(StringUtils.isEmpty(ase.getCreatedFromDate()) && StringUtils.isNotEmpty(ase.getCreatedToDate())){
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.CREATED_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL,Bytes.toBytes(ase.getCreatedToDate()));
            filterList.addFilter(filter);
        }


        if(StringUtils.isNotEmpty(ase.getClosedFromDate()) && StringUtils.isNotEmpty(ase.getClosedToDate())){
            Filter closedStartDateFilter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL,Bytes.toBytes(ase.getClosedFromDate()));
            Filter closedEndDateFilter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL,Bytes.toBytes(ase.getClosedToDate()));
            filterList.addFilter(closedStartDateFilter);
            filterList.addFilter(closedEndDateFilter);
        }

        if(StringUtils.isNotEmpty(ase.getClosedFromDate()) && StringUtils.isEmpty(ase.getClosedToDate())){
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL,Bytes.toBytes(ase.getClosedFromDate()));
            filterList.addFilter(filter);
        }

        if(StringUtils.isEmpty(ase.getClosedFromDate()) && StringUtils.isNotEmpty(ase.getClosedToDate())){
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_DATE),
                    CompareFilter.CompareOp.LESS_OR_EQUAL,Bytes.toBytes(ase.getClosedToDate()));
            filterList.addFilter(filter);
        }

        if(StringUtils.isNotEmpty(ase.getColsedBy())){
            Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.LAST_UPDATE_BY),
                    CompareFilter.CompareOp.EQUAL,new RegexStringComparator(
                    ase.getColsedBy()+"\\w|\\w"+ase.getColsedBy()+"\\w|\\w"+ase.getColsedBy()));
            filterList.addFilter(filter);
        }

        if(null != ase.getCustomerIdList()){
            for(String cId : ase.getCustomerIdList()){
                filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                        Bytes.toBytes(WorkObjSchema.customerId),
                        CompareFilter.CompareOp.EQUAL, Bytes.toBytes(cId)));
            }
        }

        //TODO
        if(StringUtils.isNotEmpty(ase.getSuspiciousLevel())){
            //Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.),CompareFilter.CompareOp.LESS_OR_EQUAL,Bytes.toBytes(ase.getClosedToDate()));
            //filterList.addFilter(filter);
        }

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
                case WorkObjSchema.createdBy:
                    String createdBy = Bytes.toString(CellUtil.cloneValue(c));
                    o.setCreatedBy(createdBy);
                    break;
                case WorkObjSchema.createdDate:
                    String createdDate = Bytes.toString(CellUtil.cloneValue(c));
                    o.setCreationDate(FormatUtils.StringToDate(createdDate));
                    break;
                case WorkObjSchema.updateBy:
                    String updateBy = Bytes.toString(CellUtil.cloneValue(c));
                    o.setLastUpdatedBy(updateBy);
                    break;

                case WorkObjSchema.updateDate:
                    String updateDate = Bytes.toString(CellUtil.cloneValue(c));
                    o.setLastUpdateDate(FormatUtils.StringToDate(updateDate));
                    break;
                case WorkObjSchema.isActive:
                    String isActive = Bytes.toString(CellUtil.cloneValue(c));
                    o.setStatus(isActive.equals("true"));
                    break;
                case WorkObjSchema.customerId:
                    o.setCustomerId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
            }
        }

        return o;
    }

    public void truncateTable() throws IOException {
        hbaseDao.deleteTable(tableKey);
        hbaseDao.createTable(tableKey);
    }

    public void initial() throws Exception {
        table = hbaseDao.getTable(tableKey);
    }

    public void initial(String rowKey) throws Exception {
        initial();
        this.rowKey = rowKey;
    }

    public void saveColumn(String key, String value) throws Exception {
        hbaseDao.putData(table, rowKey, "f1", key, value);
    }


    public static void main(String[] args) {
        String[] str = {"0000001", "000002", "00020", "0000032"};
        String str1 = "20170810165819309546137955962109";
        String keyWord = "2";
        Pattern pn = Pattern.compile(keyWord+"\\w|\\w"+keyWord+"\\w|\\w"+keyWord);
        String keyWord2 = "2017";
        //Pattern pn = Pattern.compile(keyWord2+"\\w");
        //Pattern pn = Pattern.compile(keyWord2+"[.]*");
        Matcher mr = null;
        /**
        for (String s : str) {
            mr = pn.matcher(s);
            if (mr.find())
                System.out.println(s);
        }
         **/
        mr = pn.matcher(str1);
        if (mr.find()){
            System.out.println(str1);
        }
    }


}
