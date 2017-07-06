package com.pwc.aml.transation.service.imp;

import com.pwc.aml.transation.dao.IHbaseDao;
import com.pwc.aml.transation.service.ITransactionService;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aliu323 on 7/4/2017.
 */
@Service
public class TransactionServiceImp implements ITransactionService {
    @Autowired
    private IHbaseDao hbaseDaoImp;
    private String tableName="aml:trans";

    @Override
    public void truncateTable() throws IOException {
        hbaseDaoImp.deleteTable(tableName);
        hbaseDaoImp.createTable(tableName);
    }

    @Override
    public void putData(String table, String rowkey, String columnFamily, String column, String value) throws Exception {
        HTable hTable = hbaseDaoImp.getTable(table);
        hbaseDaoImp.putData(hTable, rowkey, columnFamily, column, value);
    }

    @Override
    public void deleteData(String table, String rowkey, String columnFamily, String column) throws Exception {
        HTable hTable = hbaseDaoImp.getTable(table);
        hbaseDaoImp.deleteData(hTable, rowkey, columnFamily, column);
    }

    @Override
    public void deleteByColumnFamily(String table, String rowkey, String columnFamily, String column) throws Exception {
        HTable hTable = hbaseDaoImp.getTable(table);
        hbaseDaoImp.deleteByColumnFamily(hTable, rowkey, columnFamily);
    }

    @Override
    public Map<String, String> getData(String table, String rowKey, String columnFamily) throws Exception {
        HTable hTable = hbaseDaoImp.getTable(table);
        Cell[] cells = hbaseDaoImp.getData(hTable, rowKey, columnFamily);
        Map<String, String> map = new HashMap<String, String>();
        for (Cell cell : cells) {
            map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));

        }
        return map;
    }

    @Override
    public void createTable(String tableName) throws IOException {
        hbaseDaoImp.createTable(tableName);

    }

    @Override
    public void deleteTable(String tableName) throws IOException {
        hbaseDaoImp.deleteTable(tableName);
    }

    public void importData() throws Exception {
        hbaseDaoImp.importTsv();
    }

}
