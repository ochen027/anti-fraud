package com.pwc.aml.suppress.dao;


import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.customers.entity.CustomerBase;
import com.pwc.aml.rules.dao.IRulesDAO;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.aml.suppress.entity.SuppressSchema;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkObjSchema;
import com.pwc.common.base.dao.HadoopBaseDao;
import com.pwc.common.util.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class SuppressDao extends HadoopBaseDao implements ISuppressDao {

    @Autowired
    private ICustomerBaseDao customerBaseDao;

    @Autowired
    private IAlertDAO alertDAO;

    @Autowired
    private IRulesDAO rulesDAO;

    public SuppressDao() {
        this.tableKey = "aml:suppress";
    }

    @Override
    public void createOrUpdate(Suppress suppress) throws Exception {
        initial(suppress.getSuppressId());
        saveColumn(SuppressSchema.suppressId, suppress.getSuppressId());
        saveColumn(SuppressSchema.customersId, suppress.getCustomerBase().getCustomerId());
        saveColumn(SuppressSchema.scenarioId, suppress.getScenario().getId());
        saveColumn(SuppressSchema.isPermanent, suppress.isPermanent());
        saveColumn(SuppressSchema.endDate, suppress.getEndDate().getTime());
        saveColumn(SuppressSchema.alertId,suppress.getAlerts().getAlertId());
        saveColumn(SuppressSchema.createdBy, suppress.getCreatedBy());
        saveColumn(SuppressSchema.createdDate, suppress.getCreationDate().getTime());
        saveColumn(SuppressSchema.updateBy, suppress.getLastUpdatedBy());
        saveColumn(SuppressSchema.updateDate, suppress.getLastUpdateDate().getTime());
        saveColumn(SuppressSchema.isActive, suppress.isStatus());
    }
    private FilterList generateFilterList(AlertSearchEntity ase, FilterList filterList) throws ParseException {
        //Filter Alert Id
        if (StringUtils.isNotEmpty(ase.getAlertId())) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.alertId),
                    CompareFilter.CompareOp.EQUAL, new RegexStringComparator("[.]*" + ase.getAlertId() + "[.]*")));
        }


//        //Filter Account Id
//        if (StringUtils.isNotEmpty(ase.getAccountId())) {
//            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.isActive),
//                    CompareFilter.CompareOp.EQUAL, new RegexStringComparator("[.]*" + ase.getAccountId() + "[.]*")));
//        }


//        //Filter Total Amount Value
//        if (null != ase.getTotalAmt()) {
//            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.isActive),
//                    CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(ase.getTotalAmt()))));
//        }


        //Filter Scenario
        if (null != ase.getScenarioId()) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.scenarioId),
                    CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(ase.getScenarioId()))));
        }

        //Filter Alert Created From Date to Date
        if (null != ase.getCreatedFromDate() && null != ase.getCreatedToDate()) {
            LocalDate lDateFrom = FormatUtils.DateToLocalDate(ase.getCreatedFromDate());
            Date fromDate = FormatUtils.LocalDateToDate(lDateFrom);
            LocalDate lDateTo = FormatUtils.DateToLocalDate(ase.getCreatedToDate());
            Date toDate = FormatUtils.LocalDateToDate(lDateTo);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.createdDate),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes(fromDate.getTime()))));
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.createdDate),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes(toDate.getTime()))));
        }

        if (null != ase.getCreatedFromDate() && null == ase.getCreatedToDate()) {
            LocalDate lDateFrom = FormatUtils.DateToLocalDate(ase.getCreatedFromDate());
            Date fromDate = FormatUtils.LocalDateToDate(lDateFrom);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.createdDate),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(fromDate.getTime())));
        }

        if (null == ase.getCreatedFromDate() && null != ase.getCreatedToDate()) {
            LocalDate lDateTo = FormatUtils.DateToLocalDate(ase.getCreatedToDate());
            Date toDate = FormatUtils.LocalDateToDate(lDateTo);
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(SuppressSchema.createdDate),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(toDate.getTime())));
        }

        if(null!=ase.getActive()&&true==ase.getActive()){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                    Bytes.toBytes(SuppressSchema.isActive),
                    CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("true"))));
        }



        if (StringUtils.isNotEmpty(ase.getSuspiciousLevel())) {
            //TODO
        }

        return filterList;
    }

    @Override
    public List<Suppress> findAll() throws Exception {
        initial();
        Scan scan = new Scan();
        ResultScanner rsscan = table.getScanner(scan);
        List<Suppress> tList = new ArrayList<Suppress>();
        for (Result r : rsscan) {
            Suppress t = this.CellToSuppress(r.rawCells());
            tList.add(t);
        }
        rsscan.close();
        return tList;
    }
    @Override
    public List<Suppress> findSuppress( AlertSearchEntity ase) throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

        filterList = this.generateFilterList(ase, filterList);

        if (null != ase.getCustomerIdList()&& !ase.getAllCustomer()) {
            List<Suppress> supList = new ArrayList<Suppress>();
            for(String cId : ase.getCustomerIdList()) {

                filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                        Bytes.toBytes(SuppressSchema.customersId),
                        CompareFilter.CompareOp.EQUAL, Bytes.toBytes(cId)));
                scan.setFilter(filterList);
                ResultScanner rsscan = table.getScanner(scan);
                List<Suppress> sList = new ArrayList<Suppress>();
                for (Result r : rsscan) {
                    Suppress s = this.CellToSuppress(r.rawCells());
                    sList.add(s);
                }
                rsscan.close();
                supList.addAll(sList);

            }
            return supList;

        } else{
            if(filterList.getFilters().size()!=0)
            scan.setFilter(filterList);
            ResultScanner rsscan = table.getScanner(scan);
            List<Suppress> supList = new ArrayList<Suppress>();
            for (Result r : rsscan) {
                Suppress s = this.CellToSuppress(r.rawCells());
                supList.add(s);
            }
            rsscan.close();
            return supList;
        }
    }


    @Override
    public List<Suppress> findAllActive() throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(SuppressSchema.isActive),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes("true")));
        scan.setFilter(filterList);
        ResultScanner rsscan = table.getScanner(scan);
        List<Suppress> tList = new ArrayList<Suppress>();
        for (Result r : rsscan) {
            Suppress t = this.CellToSuppress(r.rawCells());
            tList.add(t);
        }
        rsscan.close();
        return tList;
    }

    @Override
    public Suppress findSupressById(String id) throws Exception {
        initial();
        Scan scan = new Scan();
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(id.getBytes()));
        scan.setFilter(filter);
        ResultScanner rsscan = table.getScanner(scan);
        Suppress supress = new Suppress();
        for (Result r : rsscan) {
            supress = this.CellToSuppress(r.rawCells());
        }
        rsscan.close();
        return supress;
    }


    private Suppress CellToSuppress(Cell[] cells) throws Exception {
        Suppress suppress = new Suppress();
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch (key) {
                case SuppressSchema.suppressId:
                    suppress.setSuppressId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case SuppressSchema.customersId:
                    String customerId = Bytes.toString(CellUtil.cloneValue(c));
                    CustomerBase customerBase = customerBaseDao.findByCustId(customerId);
                    suppress.setCustomerBase(customerBase);
                    break;
                case SuppressSchema.scenarioId:
                    String scenarioId = Bytes.toString(CellUtil.cloneValue(c));
                    Scenario scenario = rulesDAO.findSingleScenario(Integer.parseInt(scenarioId));
                    suppress.setScenario(scenario);
                    break;
                case SuppressSchema.isPermanent:
                    suppress.setPermanent("true".equalsIgnoreCase(Bytes.toString(CellUtil.cloneValue(c))));
                    break;
                case SuppressSchema.endDate:
                    Long endDate = Bytes.toLong(CellUtil.cloneValue(c));
                    suppress.setEndDate(new Date(endDate));
                    break;
                case SuppressSchema.alertId:
                    String alertId = Bytes.toString(CellUtil.cloneValue(c));
                    Alerts alerts= alertDAO.getSingleAlert(alertId);
                    suppress.setAlerts(alerts);
                    break;
                case SuppressSchema.createdBy:
                    suppress.setCreatedBy(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case SuppressSchema.createdDate:
                    Long createDate = Bytes.toLong(CellUtil.cloneValue(c));
                    suppress.setCreationDate(new Date(createDate));
                    break;
                case SuppressSchema.updateBy:
                    suppress.setLastUpdatedBy(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case SuppressSchema.updateDate:
                    Long updateDate = Bytes.toLong(CellUtil.cloneValue(c));
                    suppress.setLastUpdateDate(new Date(updateDate));
                    break;
                case SuppressSchema.isActive:
                    suppress.setStatus("true".equalsIgnoreCase(Bytes.toString(CellUtil.cloneValue(c))));
                    break;
            }
        }

        return suppress;
    }

}
