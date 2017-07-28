package com.pwc.aml.workflow.dao;

import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkObjSchema;
import org.apache.hadoop.hbase.client.HTable;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkObjDao implements IWorkObjDao {

    @Autowired
    private IHbaseDao hbaseDao;

    private HTable table;

    private String rowKey;

    private String tableKey="aml:trans";

    @Override
    public void save(WorkObj workObj) throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        initial(workObj.getWorkObjId());
        saveColumn(WorkObjSchema.workObjectId,workObj.getWorkObjId());
        saveColumn(WorkObjSchema.flowId,workObj.getWorkflow().getFlowId());
        saveColumn(WorkObjSchema.currentPointId,workObj.getCurrentPoint().getFlowPointId());
        saveColumn(WorkObjSchema.historyEvents, mapper.writeValueAsString(workObj.getHistoryEvents()));
        saveColumn(WorkObjSchema.roleId,""+workObj.getCurrentPoint().getRoleId());
    }

    public void initial(String rowKey) throws Exception {
        table = hbaseDao.getTable(tableKey);
        this.rowKey=rowKey;
    }

    public void saveColumn(String key, String value) throws Exception {
        hbaseDao.putData(table,rowKey,"f1", key,value);
    }

}
