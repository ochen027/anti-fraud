package com.pwc.aml.hbase.dao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * Created by aliu323 on 6/30/2017.
 */
public interface IHbaseDao {
    HTable getTable(String name) throws Exception;
    public  void putData(HTable table,String rowkey,String columnFamily,String column,String value) throws Exception;
    public  void deleteData(HTable table,String rowkey,String columnFamily,String column) throws Exception;
    public  void deleteByColumnFamily(HTable table,String rowkey,String columnFamily,String column) throws Exception ;
    public  Cell[]  getData(HTable table,String rowKey,String columnFamily) throws Exception;


    }
