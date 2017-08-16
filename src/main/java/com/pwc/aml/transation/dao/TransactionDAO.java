package com.pwc.aml.transation.dao;

import com.jcraft.jsch.JSchException;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.common.util.RunShellTool;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.common.util.FormatUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
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
            result = tool.execSSH("../../../data/Hadoop/cdh/hadoop-2.5.0-cdh5.3.6/bin/yarn jar ../../data/Hadoop/cdh/hbase-0.98.6-hadoop2/lib/hbase-server-0.98.6-hadoop2.jar importtsv -Dimporttsv.columns=HBASE_ROW_KEY,f1:trans_id,f1:as_of_date,f1:acct_id,f1:trans_seq,f1:trans_cdt_cd,f1:currency_cd,f1:trans_base_amt,f1:trans_type,f1:trans_desc,f1:trans_dt,f1:counterparty_id_1,f1:trans_br,f1:trans_by,f1:counterparty_nm,f1:counterbank,f1:counterbank_location aml:trans /user/hadoop/tmp/sampleData/tsvImport/trans_20170727");
            //result = tool.execSSH("../../../data/Hadoop/cdh/hadoop-2.5.0-cdh5.3.6/bin/yarn jar ../../data/Hadoop/cdh/hbase-0.98.6-hadoop2/lib/hbase-server-0.98.6-hadoop2.jar importtsv -Dimporttsv.columns=HBASE_ROW_KEY,f1:trans_id,f1:as_of_date,f1:acct_id,f1:trans_seq,f1:trans_chanel,f1:trans_cdt_cd,f1:currency_cd,f1:trans_base_amt,f1:trans_desc,f1:trans_dt,f1:counterparty_id_1,f1:trans_br,f1:trans_by aml:trans /user/hadoop/tmp/sampleData/tsvImport/trans_date");
        } catch (IOException | JSchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transactions> getAllTransData() throws Exception {
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable(Constants.HBASE_TABLE_TRANS);
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Transactions> list = new ArrayList<Transactions>();
        for (Result rs : rsscan) {
            Transactions tbean = this.consistTrans(rs.rawCells(), Bytes.toString(rs.getRow()));
            list.add(tbean);
        }
        return list;
    }

    @Override
    public Transactions getSingleTrans(String transId) throws Exception {
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_TRANS);
        Scan scan = new Scan();
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("f1"), Bytes.toBytes("trans_id"),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(transId));
        scan.setFilter(filter);
        ResultScanner rsscan = table.getScanner(scan);
        List<Transactions> tList = new ArrayList<Transactions>();
        for (Result r : rsscan) {
            Transactions t = this.consistTrans(r.rawCells(), Bytes.toString(r.getRow()));
            tList.add(t);
        }
        rsscan.close();
        return tList.get(0);
    }

    @Override
    public List<Transactions> getTransListById(String[] transIds) throws Exception {
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_TRANS);
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        for(String id : transIds){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                    Bytes.toBytes("trans_id"),
                    CompareFilter.CompareOp.EQUAL,Bytes.toBytes(id)));
        }
        scan.setFilter(filterList);
        ResultScanner rsscan = table.getScanner(scan);
        List<Transactions> tList = new ArrayList<Transactions>();
        for (Result r : rsscan) {
            Transactions t = this.consistTrans(r.rawCells(), Bytes.toString(r.getRow()));
            tList.add(t);
        }
        rsscan.close();
        return tList;
    }

    @Override
    public void TruncateTrans() throws Exception {
        hBaseDAO.deleteTable(Constants.HBASE_TABLE_TRANS);
        hBaseDAO.createTable(Constants.HBASE_TABLE_TRANS);
    }

    @Override
    public List<Transactions> getTransDataByAccount(List<String> aIdList, String ruleDays, String businessDate) throws Exception {
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_TRANS);
        Scan scan = new Scan();
        LocalDate validDate = FormatUtils.StringToLocalDate(businessDate);
        LocalDate FromDate = validDate.minusDays(Long.parseLong(ruleDays)+1L);
        scan.setStartRow(Bytes.toBytes(FormatUtils.LocalDateToString(FromDate)));
        scan.setStopRow(Bytes.toBytes(FormatUtils.LocalDateToString(validDate)));
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        for(String accountId : aIdList){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                    Bytes.toBytes("acct_id"),
                    CompareFilter.CompareOp.EQUAL,Bytes.toBytes(accountId)));
        }
        scan.setFilter(filterList);
        ResultScanner rsscan = table.getScanner(scan);
        List<Transactions> tList = new ArrayList<Transactions>();
        for (Result rs : rsscan) {
            Transactions t = this.consistTrans(rs.rawCells(), Bytes.toString(rs.getRow()));
            tList.add(t);
        }
        rsscan.close();
        return tList;
    }

    @Override
    public Integer getTransByDateCount(String businessDate) throws Exception {
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_TRANS);
        Scan scan = new Scan();
        LocalDate fromDate = FormatUtils.StringToLocalDateNoDash(businessDate).minusDays(1L);
        LocalDate toDate = FormatUtils.StringToLocalDateNoDash(businessDate).plusDays(1L);
        scan.setStartRow(Bytes.toBytes(FormatUtils.LocalDateToString(fromDate)));
        scan.setStopRow(Bytes.toBytes(FormatUtils.LocalDateToString(toDate)));
        return this.getResultCount(table.getScanner(scan));
    }

    private Transactions consistTrans(Cell[] cells, String transId) throws ParseException{
        Transactions tbean = new Transactions();
        tbean.setTransId(transId);
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case "acct_id":
                    tbean.setAcctId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "as_of_date":
                    tbean.setAsOfDate(FormatUtils.StringToLocalDateNoDash(Bytes.toString(CellUtil.cloneValue(c))).toString());
                    continue;
                case "counterparty_id_1":
                    tbean.setCounterPartyId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "currency_cd":
                    tbean.setCurrencyCd(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_base_amt":
                    tbean.setTransBaseAmt(new BigDecimal(Bytes.toString(CellUtil.cloneValue(c)).replace(",", "")));
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
                case "trans_type":
                    tbean.setTransType(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_desc":
                    tbean.setTransDesc(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_dt":
                    tbean.setTransDt(FormatUtils.StringToLocalDateNoDash(Bytes.toString(CellUtil.cloneValue(c))).toString());
                    continue;
                case "trans_seq":
                    tbean.setTransSeq(Integer.parseInt(Bytes.toString(CellUtil.cloneValue(c))));
                    continue;
                case "counterbank":
                    tbean.setCounterBank(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "counterbank_location":
                    tbean.setCounterBankLocation(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "counterparty_nm":
                    tbean.setCounterName(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "trans_id":
                    tbean.setTransId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
            }

        }
        return tbean;
    }


    private Integer getResultCount(ResultScanner results){
        int i =0;
        for (Result r : results) {
            i++;
        }
        return i;
    }
}
