package com.pwc.aml.transation.service;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aliu323 on 7/4/2017.
 */
public interface ITransactionService {
    void truncateTable() throws IOException;

    void putData(String tableName, String rowkey, String columnFamily, String column, String value) throws Exception;

    void deleteData(String tableName, String rowkey, String columnFamily, String column) throws Exception;

    void deleteByColumnFamily(String tableName, String rowkey, String columnFamily, String column) throws Exception;

    Map<String, String> getData(String tableName, String rowKey, String columnFamily) throws Exception;

    void createTable(String tableName) throws IOException;

    void deleteTable(String tableName) throws IOException;

    void importData()throws Exception;
}
