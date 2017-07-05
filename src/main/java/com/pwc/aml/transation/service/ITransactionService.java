package com.pwc.aml.transation.service;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aliu323 on 7/4/2017.
 */
public interface ITransactionService {
    public void truncateTable(String tableName) throws IOException;
    public  void putData(String tableName, String rowkey, String columnFamily, String column, String value) throws Exception;
    public  void deleteData(String tableName,String rowkey,String columnFamily,String column) throws Exception;
    public  void deleteByColumnFamily(String tableName,String rowkey,String columnFamily,String column) throws Exception ;
    public Map<String,String> getData(String tableName, String rowKey, String columnFamily) throws Exception;
    public void createTable(String tableName) throws IOException;
    public void deleteTable(String tableName) throws IOException;
}
