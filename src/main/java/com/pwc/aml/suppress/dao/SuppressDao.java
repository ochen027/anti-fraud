package com.pwc.aml.suppress.dao;


import com.pwc.aml.common.util.Constants;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.rules.dao.IRulesDAO;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.aml.suppress.entity.SuppressSchema;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkObjSchema;
import com.pwc.common.base.dao.HadoopBaseDao;
import com.pwc.common.util.FormatUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class SuppressDao extends HadoopBaseDao implements ISuppressDao {

    @Autowired
    private ICustomerDAO customerDAO;

    @Autowired
    private IRulesDAO rulesDAO;

    public SuppressDao() {
        this.tableKey = "aml:suppress";
    }

    @Override
    public void createOrUpdate(Suppress suppress) throws Exception {
        initial(suppress.getSuppressId());
        saveColumn(SuppressSchema.suppressId, suppress.getSuppressId());
        saveColumn(SuppressSchema.customersId, suppress.getCustomers().getCustomerId());
        saveColumn(SuppressSchema.scenarioId, suppress.getScenario().getId());
        saveColumn(SuppressSchema.isPermanent, suppress.isPermanent());
        saveColumn(SuppressSchema.endDate, suppress.getEndDate().getTime());
        saveColumn(SuppressSchema.createdBy, suppress.getCreatedBy());
        saveColumn(SuppressSchema.createdDate, suppress.getCreationDate().getTime());
        saveColumn(SuppressSchema.updateBy, suppress.getLastUpdatedBy());
        saveColumn(SuppressSchema.updateDate, suppress.getLastUpdateDate().getTime());
        saveColumn(SuppressSchema.isActive, suppress.isStatus());
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
    public List<Suppress> findAllActive() throws Exception {
        initial();
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(SuppressSchema.isActive),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(true)));
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
                    Customers customers = customerDAO.findByCustCtNo(customerId);
                    suppress.setCustomers(customers);
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
