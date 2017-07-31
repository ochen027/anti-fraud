package com.pwc.aml.workflow.dao;

import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkObjSchema;
import com.pwc.component.workflow.dao.IFlowPointDAO;
import com.pwc.component.workflow.dao.IWorkflowDAO;
import com.pwc.component.workflow.entity.FlowEvent;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkObjDao implements IWorkObjDao {

    @Autowired
    private IHbaseDao hbaseDao;


    @Autowired
    private IWorkflowExDao workflowExDao;


    @Autowired
    private IFlowPointExDao flowPointExDao;

    private HTable table;

    private String rowKey;

    private String tableKey = "aml:trans";

    @Override
    public void save(WorkObj workObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        initial(workObj.getWorkObjId());
        saveColumn(WorkObjSchema.workObjectId, workObj.getWorkObjId());
        saveColumn(WorkObjSchema.flowId, workObj.getWorkflow().getFlowId());
        saveColumn(WorkObjSchema.currentPointId, workObj.getCurrentPoint().getFlowPointId());
        saveColumn(WorkObjSchema.historyEvents, mapper.writeValueAsString(workObj.getHistoryEvents()));
        saveColumn(WorkObjSchema.roleId, "" + workObj.getCurrentPoint().getRoleId());
    }

    @Override
    public WorkObj findWorkObjByWorkObjId(String workObjId) throws Exception {
        initial();
        Cell[] cells = hbaseDao.getData(table, workObjId, "f1");// find by workObjId();

        return null;
    }

    @Override
    public List<WorkObj> findWorkObjsByPointId(String flowPointId) throws Exception {
        initial();

        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                Bytes.toBytes(WorkObjSchema.currentPointId),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(flowPointId)));

        ResultScanner rsscan = table.getScanner(scan);
        List<WorkObj> tList = new ArrayList<WorkObj>();
        for (Result r : rsscan) {
            WorkObj t = this.CellToWorkObj(r.rawCells());
            tList.add(t);
        }
        rsscan.close();
        return tList;
    }


    private WorkObj CellToWorkObj(Cell[] cells) throws IOException {
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
                    o.setWorkflow(workflowExDao.getWorkflowByFlowId(flowId));
                    break;
                case WorkObjSchema.currentPointId:
                    String currentPointId = Bytes.toString(CellUtil.cloneValue(c));
                    FlowPointEx flowPointEx = flowPointExDao.getFlowPointExByPointId(currentPointId);
                    o.setCurrentPoint(flowPointEx);
                    break;
                case WorkObjSchema.historyEvents:
                    String json = Bytes.toString(CellUtil.cloneValue(c));
                    List<FlowEvent> flowEvents = (List<FlowEvent>) mapper.readValue(json, List.class);
                    o.setHistoryEvents(flowEvents);
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
