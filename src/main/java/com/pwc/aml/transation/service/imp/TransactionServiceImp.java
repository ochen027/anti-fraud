package com.pwc.aml.transation.service.imp;

import com.pwc.aml.transation.dao.IHbaseDao;
import com.pwc.aml.transation.service.ITransactionService;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by aliu323 on 7/4/2017.
 */
@Service
public class TransactionServiceImp implements ITransactionService {
    @Autowired
    IHbaseDao hbaseDaoImp;
    @Override
    public void truncateTable(String tableName) throws IOException {
        hbaseDaoImp.deleteTable(tableName);
        hbaseDaoImp.createTable(tableName);
    }

    @Override
    public void putData(String table, String rowkey, String columnFamily, String column, String value) throws Exception {
        HTable hTable=hbaseDaoImp.getTable(table);
        hbaseDaoImp.putData(hTable,rowkey,columnFamily,column,value);
    }

    @Override
    public void deleteData(String table, String rowkey, String columnFamily, String column) throws Exception {
        HTable hTable=hbaseDaoImp.getTable(table);
        hbaseDaoImp.deleteData(hTable,rowkey, columnFamily,column);
    }

    @Override
    public void deleteByColumnFamily(String table, String rowkey, String columnFamily, String column) throws Exception {
        HTable hTable=hbaseDaoImp.getTable(table);
        hbaseDaoImp.deleteByColumnFamily(hTable,rowkey, columnFamily);
    }

    @Override
    public Cell[] getData(String table, String rowKey, String columnFamily) throws Exception {
        HTable hTable=hbaseDaoImp.getTable(table);
        return hbaseDaoImp.getData(hTable,rowKey,columnFamily);
    }

    @Override
    public void createTable(String tableName) throws IOException {
        hbaseDaoImp.createTable(tableName);

    }

    @Override
    public void deleteTable(String tableName) throws IOException {
        hbaseDaoImp.deleteTable(tableName);
    }
}
