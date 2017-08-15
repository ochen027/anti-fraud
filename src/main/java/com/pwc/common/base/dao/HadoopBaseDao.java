package com.pwc.common.base.dao;

import com.pwc.aml.common.hbase.HbaseDaoImp;
import com.pwc.aml.common.util.Constants;
import org.apache.hadoop.hbase.client.HTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public abstract class HadoopBaseDao {

    @Autowired
    protected HbaseDaoImp hbaseDao;

    protected HTable table;

    protected String rowKey;

    protected String tableKey = "aml:tableName";



    public void truncateTable() throws IOException {
        hbaseDao.deleteTable(tableKey);
        hbaseDao.createTable(tableKey);
    }


    public void saveColumn(String key, Object value) throws Exception {
        if(value instanceof Double){
            hbaseDao.putData(table, rowKey, Constants.F1, key, (Double)value);
        }else if(value instanceof Long){
            hbaseDao.putData(table, rowKey, Constants.F1, key, (Long)value);
        }else{
            hbaseDao.putData(table, rowKey, Constants.F1, key, String.valueOf(value));
        }
    }

    public void initial() throws Exception {
        table = hbaseDao.getTable(tableKey);
    }

    public void initial(String rowKey) throws Exception {
        initial();
        this.rowKey = rowKey;
    }

}
