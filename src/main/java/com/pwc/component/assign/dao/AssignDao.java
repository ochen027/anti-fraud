package com.pwc.component.assign.dao;


import com.pwc.aml.common.util.Constants;
import com.pwc.component.assign.entity.Assign;
import com.pwc.component.assign.entity.AssignHistory;
import com.pwc.component.assign.entity.AssignSchema;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.common.util.FormatUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AssignDao implements IAssignDao {

    @Autowired
    private IHbaseDao hbaseDao;

    private HTable table;

    private String rowKey;

    private String tableKey = "aml:assign";


    @Override
    public void save(Assign assign) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        initial(assign.getObjId());
        saveColumn(AssignSchema.assignId, assign.getAssignId());
        saveColumn(AssignSchema.uObjId, assign.getuObjId());
        saveColumn(AssignSchema.objId, assign.getObjId());
        saveColumn(AssignSchema.byId, assign.getById());
        saveColumn(AssignSchema.history, mapper.writeValueAsString(assign.getHistory()));
        saveColumn(AssignSchema.createdBy, assign.getCreatedBy());
        saveColumn(AssignSchema.createdDate, FormatUtils.DateToString(assign.getCreationDate()));
        saveColumn(AssignSchema.updateBy, assign.getLastUpdatedBy());
        saveColumn(AssignSchema.updateDate, FormatUtils.DateToString(assign.getLastUpdateDate()));
        saveColumn(AssignSchema.isActive, assign.isStatus() ? "true" : "false");
    }

    @Override
    public List<Assign> findByUserId(String userId) throws Exception {
        initial();

        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                Bytes.toBytes(AssignSchema.uObjId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(userId)));
        scan.setFilter(filterList);
        ResultScanner rsscan = table.getScanner(scan);
        List<Assign> tList = new ArrayList<Assign>();
        for (Result r : rsscan) {
            Assign t = this.CellToAssign(r.rawCells());
            tList.add(t);
        }
        rsscan.close();
        return tList;

    }

    @Override
    public Assign findByObjId(String objId) throws Exception {
        initial();
        Scan scan = new Scan();
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1),
                Bytes.toBytes(AssignSchema.objId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(objId));
        scan.setFilter(filter);
        ResultScanner rsscan = table.getScanner(scan);
        List<Assign> tList = new ArrayList<Assign>();
        for (Result r : rsscan) {
            Assign t = this.CellToAssign(r.rawCells());
            tList.add(t);
        }
        rsscan.close();
        if (tList.size() == 1) {
            return tList.get(0);
        } else {
            return null;
        }
    }

    private Assign CellToAssign(Cell[] cells) throws ParseException, IOException {

        Assign o = new Assign();
        ObjectMapper mapper = new ObjectMapper();
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch (key) {
                case AssignSchema.assignId:
                    o.setAssignId(Bytes.toString(CellUtil.cloneValue(c)));
                    break;
                case AssignSchema.uObjId:
                    String userId = Bytes.toString(CellUtil.cloneValue(c));
                    o.setuObjId(userId);
                    break;
                case AssignSchema.objId:
                    String objectId = Bytes.toString(CellUtil.cloneValue(c));
                    o.setObjId(objectId);
                    break;
                case AssignSchema.byId:
                    String byId = Bytes.toString(CellUtil.cloneValue(c));
                    o.setById(byId);
                    break;
                case AssignSchema.history:
                    String history = Bytes.toString(CellUtil.cloneValue(c));
                    o.setHistory((List<AssignHistory>) mapper.readValue(history, List.class));
                    break;
                case AssignSchema.createdBy:
                    String createdBy = Bytes.toString(CellUtil.cloneValue(c));
                    o.setCreatedBy(createdBy);
                    break;
                case AssignSchema.createdDate:
                    String createdDate = Bytes.toString(CellUtil.cloneValue(c));
                    o.setCreationDate(FormatUtils.StringToDate(createdDate));
                    break;
                case AssignSchema.updateBy:
                    String updateBy = Bytes.toString(CellUtil.cloneValue(c));
                    o.setLastUpdatedBy(updateBy);
                    break;

                case AssignSchema.updateDate:
                    String updateDate = Bytes.toString(CellUtil.cloneValue(c));
                    o.setLastUpdateDate(FormatUtils.StringToDate(updateDate));
                    break;
                case AssignSchema.isActive:
                    String isActive = Bytes.toString(CellUtil.cloneValue(c));
                    o.setStatus(isActive.equals("true"));
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
}
