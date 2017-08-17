package com.pwc.aml.alert.dao;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.hbase.HbaseDaoImp;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.transation.dao.ITransactionDAO;
import com.pwc.aml.transation.entity.Transactions;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlertDAO implements IAlertDAO {

    @Autowired
    private HbaseDaoImp hbaseDao;

    @Autowired
    private ITransactionDAO transactionDAO;

    @Autowired
    private ICustomerBaseDao customerBaseDAO;

    @Override
    public Alerts getSingleAlert(String alertId) throws Exception {
        Cell[] cells= hbaseDao.getData(hbaseDao.getTable(Constants.HBASE_TABLE_ALERT),alertId, Constants.F1);
        return this.consistAlerts(cells, alertId);
    }

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        Scan scan = new Scan();
        HTable hTable = hbaseDao.getTable(Constants.HBASE_TABLE_ALERT);
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Alerts> list = new ArrayList<Alerts>();
        for (Result rs : rsscan) {
            Alerts aBean = this.consistAlerts(rs.rawCells(), Bytes.toString(rs.getRow()));
            list.add(aBean);
        }
        return list;
    }

    @Override
    public void truncateTable() throws IOException {
        hbaseDao.deleteTable(Constants.HBASE_TABLE_ALERT);
        hbaseDao.createTable(Constants.HBASE_TABLE_ALERT);
    }

    private Alerts consistAlerts(Cell[] cells, String id) throws Exception{
        Alerts aBean = new Alerts();
        aBean.setAlertId(id);
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case Constants.COLUMN_ALERT_CONTENT:
                    aBean.setAlertContents(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_ALERT_CREATED_DATE:
                    aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_ALERT_DESC:
                    aBean.setAlertDesc(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_ALERT_NAME:
                    aBean.setAlertName(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_BUSINESS_DATE:
                    aBean.setBusinessDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.CREATED_BY:
                    aBean.setCreatedBy(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.CREATED_DATE:
                    aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_CUSTOMER_ID:
                    aBean.setCustomerId(Bytes.toString(CellUtil.cloneValue(c)));
                    aBean.setCustomerName(customerBaseDAO.findByCustId(aBean.getCustomerId()).getCustomerFullName());
                    continue;
                case Constants.COLUMN_SCENARIO_ID:
                    aBean.setScenarioId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_TOTAL_AMOUNT:
                    aBean.setTotalAmt(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_TRANS_ID_ARRAY:
                    String[] transArray = Bytes.toString(CellUtil.cloneValue(c)).split(Constants.COMMA);
                    List<Transactions> tList = transactionDAO.getTransListById(transArray);
                    aBean.setTransList(tList);
                    continue;
                case Constants.COLUMN_ACCOUNT_ID:
                    aBean.setAccountId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
            }
        }
        return aBean;
    }




}
