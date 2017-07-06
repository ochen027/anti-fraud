package com.pwc.aml.transation.dao;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;

/**
 * Created by aliu323 on 6/30/2017.
 */
public interface IHbaseDao {
    HTable getTable(String name) throws Exception;
      void putData(HTable table,String rowkey,String columnFamily,String column,String value) throws Exception;
      void deleteData(HTable table,String rowkey,String columnFamily,String column) throws Exception;
      void deleteByColumnFamily(HTable table,String rowkey,String columnFamily) throws Exception ;
      Cell[]  getData(HTable table,String rowKey,String columnFamily) throws Exception;
//    public  Cell getData(HTable table,String rowKey,String columnFamily,String column) throws Exception;
     void createTable(String tableName) throws IOException;
     void deleteTable(String tableName) throws IOException;

}
