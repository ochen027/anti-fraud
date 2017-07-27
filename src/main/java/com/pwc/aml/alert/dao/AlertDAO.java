package com.pwc.aml.alert.dao;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.common.hbase.HbaseDaoImp;
import com.pwc.aml.documents.entity.Documents;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlertDAO implements IAlertDAO {

    @Autowired
    private HbaseDaoImp hBaseDAO;

    @Autowired
    private ITransactionDAO transactionDAO;

    @Override
    public Alerts getSingleAlert(String alertId) throws Exception {
        Cell[] cells= hBaseDAO.getData(hBaseDAO.getTable("aml:alerts"),alertId, "f1");
        return this.consistAlerts(cells, alertId);
    }

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable("aml:alerts");
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Alerts> list = new ArrayList<Alerts>();
        for (Result rs : rsscan) {
            Alerts aBean = this.consistAlerts(rs.rawCells(), Bytes.toString(rs.getRow()));
            list.add(aBean);
        }
        return list;
    }

    private Alerts consistAlerts(Cell[] cells, String id) throws Exception{
        Alerts aBean = new Alerts();
        aBean.setAlertId(id);
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case "alertContent":
                    aBean.setAlertContents(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "alertCreatedDate":
                    aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "alertDesc":
                    aBean.setAlertDesc(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "alertName":
                    aBean.setAlertName(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "businessDate":
                    aBean.setBusinessDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "commentId":
                    //TODO
                    aBean.setCommentsList(null);
                    continue;
                case "createdBy":
                    aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "createdDate":
                    aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "customerId":
                    aBean.setCustomerId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "documentId":
                    //TODO
                    aBean.setDocList(null);
                    continue;
                case "scenarioId":
                    aBean.setScenarioId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "totalAmt":
                    aBean.setTotalAmt(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "transIdArray":
                    String[] transArray = Bytes.toString(CellUtil.cloneValue(c)).split(",");
                    List<Transactions> tList = new ArrayList<Transactions>();
                    for(String tId : transArray){
                        Transactions trans = transactionDAO.getSingleTrans(tId);
                        tList.add(trans);
                    }
                    aBean.setTransList(tList);
                    continue;
            }
        }
        return aBean;
    }


}
