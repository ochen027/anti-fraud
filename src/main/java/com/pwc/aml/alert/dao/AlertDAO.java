package com.pwc.aml.alert.dao;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.common.hbase.HbaseDaoImp;
import com.pwc.aml.documents.entity.Documents;
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

    @Override
    public Alerts getSingleAlert(String alertId) throws Exception {
        Cell[] cells= hBaseDAO.getData(hBaseDAO.getTable("aml:alerts"),alertId, "f1");
        return this.consistAlerts(cells);
    }

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable("aml:alerts");
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Alerts> list = new ArrayList<Alerts>();
        for (Result rs : rsscan) {
            Alerts aBean = this.consistAlerts(rs.rawCells());
            list.add(aBean);
        }
        return list;
    }

    private Alerts consistAlerts(Cell[] cells){
        Alerts aBean = new Alerts();
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case "accountId":
                    aBean.setAccountId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "alertContents":
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
                    String commentArray = Bytes.toString(CellUtil.cloneValue(c));
                    Comments c1 = new Comments();
                    c1.setCommentId("COMT90001");
                    c1.setAlertId(aBean.getAlterId());
                    c1.setCommentContents("COMT90001");
                    Comments c2 = new Comments();
                    c2.setCommentId("COMT90002");
                    c2.setAlertId(aBean.getAlterId());
                    c2.setCommentContents("COMT90002");
                    List<Comments> cList = new ArrayList<Comments>();
                    cList.add(c1);
                    cList.add(c2);
                    aBean.setCommentsList(cList);
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
                    String docArray = Bytes.toString(CellUtil.cloneValue(c));
                    Documents doc1 = new Documents();
                    doc1.setAlertId(aBean.getAlterId());
                    doc1.setDocId("DOC0001");
                    doc1.setDocPath("/xxx/doc0001");
                    Documents doc2 = new Documents();
                    doc2.setAlertId(aBean.getAlterId());
                    doc2.setDocId("DOC0001");
                    doc2.setDocPath("/xxx/doc0001");
                    List<Documents> dList = new ArrayList<Documents>();
                    dList.add(doc1);
                    dList.add(doc2);
                    aBean.setDocList(dList);
                    continue;
                case "scenarioId":
                    aBean.setScenarioId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "totalAmt":
                    aBean.setTotalAmt(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "transId":
                    String transArray = Bytes.toString(CellUtil.cloneValue(c));
                    Transactions t1 = new Transactions();
                    t1.setTransId("900000000001");
                    t1.setTransBaseAmt(90000d);

                    Transactions t2 = new Transactions();
                    t2.setTransId("900000000002");
                    t2.setTransBaseAmt(20000d);

                    Transactions t3 = new Transactions();
                    t3.setTransId("900000000003");
                    t3.setTransBaseAmt(30000d);

                    List<Transactions> tList = new ArrayList<Transactions>();
                    tList.add(t1);
                    tList.add(t2);
                    tList.add(t3);
                    aBean.setTransList(tList);
                    continue;
            }
        }
        return aBean;
    }


}
