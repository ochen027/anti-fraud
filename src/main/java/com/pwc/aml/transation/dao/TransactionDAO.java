package com.pwc.aml.transation.dao;

import com.jcraft.jsch.JSchException;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.RunShellTool;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.common.util.FormatUtils;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAO implements ITransactionDAO {

    @Autowired
    private IHbaseDao hBaseDAO;


    @Override
    public void importTransData() throws Exception {
        RunShellTool tool = new RunShellTool("172.27.69.76", "hadoop", "Welcome1", 22, "utf-8");
        String result = null;
        try {
            result = tool.execSSH("../../../data/Hadoop/cdh/hadoop-2.5.0-cdh5.3.6/bin/yarn jar ../../data/Hadoop/cdh/hbase-0.98.6-hadoop2/lib/hbase-server-0.98.6-hadoop2.jar importtsv -Dimporttsv.columns=HBASE_ROW_KEY,f1:trans_id,f1:as_of_date,f1:acct_id,f1:trans_seq,f1:trans_chanel,f1:trans_cdt_cd,f1:currency_cd,f1:trans_base_amt,f1:trans_desc,f1:trans_dt,f1:counterparty_id_1,f1:trans_br,f1:trans_by aml:trans /user/hadoop/tmp/sampleData/tsvImport/trans_date");
        } catch (IOException | JSchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transactions> getAllTransData() throws Exception {
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable("aml:trans");
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Transactions> list = new ArrayList<Transactions>();
        for (Result rs : rsscan) {
            Transactions tbean = this.consistTrans(rs.rawCells());
            list.add(tbean);
        }
        return list;
    }

    @Override
    public Transactions getSingleTrans(String transId) throws Exception {
        Cell[] cells= hBaseDAO.getData(hBaseDAO.getTable("aml:trans"),transId, "f1");
        return this.consistTrans(cells);
    }

    private Transactions consistTrans(Cell[] cells) throws ParseException{
        Transactions tbean = new Transactions();
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case "acct_id":
                    tbean.setAcctId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "as_of_date":
                    tbean.setAsOfDate(FormatUtils.StringToDate(Bytes.toString(CellUtil.cloneValue(c))));
                    continue;
                case "counterparty_id_1":
                    tbean.setCounterPartyId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "currency_cd":
                    tbean.setCurrencyCd(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_base_amt":
                    tbean.setTransBaseAmt(Double.valueOf(Bytes.toString(CellUtil.cloneValue(c))));
                    continue;
                case "trans_br":
                    tbean.setTransBr(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_by":
                    tbean.setTransBy(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_cdt_cd":
                    tbean.setTransCdtCd(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_chanel":
                    tbean.setTransChanel(Integer.parseInt(Bytes.toString(CellUtil.cloneValue(c))));
                    continue;
                case "trans_desc":
                    tbean.setTransDesc(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_dt":
                    tbean.setTransDt(FormatUtils.StringToDate(Bytes.toString(CellUtil.cloneValue(c))));
                    continue;
                case "trans_seq":
                    tbean.setTransSeq(Integer.parseInt(Bytes.toString(CellUtil.cloneValue(c))));
                    continue;
            }

        }
        return tbean;
    }

}
