package com.pwc.aml.hbase.dao.imp;

/**
 * Created by aliu323 on 6/30/2017.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pwc.aml.hbase.dao.IHbaseDao;
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
public class HbaseDaoImp implements IHbaseDao {


    static  final Configuration conf = HBaseConfiguration.create();

    public  HTable getTable (String name) throws Exception{

        //get the hbase table instance
        HTable table = new HTable(conf, name);
        return table;
    }

    /**
     * put the data to the hbase table
     *
     * put :
     *         put 'tbname','rowkey','cf:col','value'
     *
     *
     * @throws Exception
     */
    public  void putData(HTable table,String rowkey,String columnFamily,String column,String value) throws Exception {
        //get the put instance
        Put put = new Put(Bytes.toBytes(rowkey));
        //conf the put

        put.add(
                Bytes.toBytes(columnFamily),
                Bytes.toBytes(column),
                Bytes.toBytes(value)
        );
        //load the put
        table.put(put);

    }

    /**
     * delete the data from the hbase table
     *
     * delete :
     *         delete 'tbname','rowkey','cf:col'
     *
     *
     * @throws Exception
     */
    public  void deleteData(HTable table,String rowkey,String columnFamily,String column) throws Exception {
        //get the delete instance
        Delete del = new Delete(Bytes.toBytes(rowkey));
        //conf the del
        //del.deleteColumn(Bytes.toBytes("info"),Bytes.toBytes("age"));
        //deleteColumns就是删除所有version
        del.deleteColumns(Bytes.toBytes(columnFamily),Bytes.toBytes(column));
        //load the del
        table.delete(del);
    }
    //delete by columnFamily
    public  void deleteByColumnFamily(HTable table,String rowkey,String columnFamily,String column) throws Exception {
        //get the delete instance
        Delete del = new Delete(Bytes.toBytes(rowkey));
        del.deleteFamily(Bytes.toBytes(columnFamily));
        table.delete(del);

    }

    /**
     * get the data from the hbase table
     *
     * get :
     *         get 'tbname','rowkey','cf:col'
     *
     * @throws Exception
     */
    public  Cell[]  getData(HTable table,String rowKey,String columnFamily) throws Exception {
        // TODO Auto-generated method stub
        Get get = new Get(Bytes.toBytes(rowKey));
        //conf the get
        //get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));

        get.addFamily(Bytes.toBytes(columnFamily));
        //load the get
        Result rs = table.get(get);
        //print the data
        Cell[] cells=rs.rawCells();
        return cells;
    }



    /**
     * scan the all table
     * scan :
     *         scan 'tbname'
     */
    public static void scanData(HTable table) throws Exception {
        //get the scan instance
        Scan scan = new Scan();
        //load the scan
        ResultScanner rsscan = table.getScanner(scan);
        for(Result rs : rsscan){
            System.out.println(Bytes.toString(rs.getRow()));
            for(Cell cell : rs.rawCells()){
                System.out.println(
                        Bytes.toString(CellUtil.cloneFamily(cell))
                                +"->"+
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                +"->"+
                                Bytes.toString(CellUtil.cloneValue(cell))
                                +"->"+
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
    }

    //pringt cells
    public static void pirntCells(Cell[] cells)
        {
        //rs.cells（）获得cells数组
        for(Cell cell : cells){
            System.out.println(
                    //用cellUtil来获取cell的值
                    Bytes.toString(CellUtil.cloneFamily(cell))
                            +"->"+
                            Bytes.toString(CellUtil.cloneQualifier(cell))
                            +"->"+
                            Bytes.toString(CellUtil.cloneValue(cell))
                            +"->"+
                            cell.getTimestamp()
            );
            System.out.println("------------------------------");
        }
    }
    /**
     * scan the table  with limit
     *      * scan :
     *         scan 'tbname',{STARTROW => 'row1',STOPROW => 'row2'}
     */
    public static void rangeData(HTable table) throws Exception {
        //get the scan instance
        Scan scan = new Scan();
        //conf the scan
        //scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
        //scan.addFamily(family);
        //scan.setStartRow(Bytes.toBytes("20161119_10002"));
        //scan.setStopRow(Bytes.toBytes("20161119_10003"));
        Filter filter = new PrefixFilter(Bytes.toBytes("9000000000"));
        scan.setFilter(filter);
        //hbase conf
        //是否启动缓存
        scan.setCacheBlocks(true);
        //设置缓存的条数
        scan.setCaching(100);
        //每一次取多少条
        scan.setBatch(10);
        //共同决定了请求RPC的次数

        //load the scan
        ResultScanner rsscan = table.getScanner(scan);
        for(Result rs : rsscan){
            System.out.println(Bytes.toString(rs.getRow()));
            for(Cell cell : rs.rawCells()){
                System.out.println(
                        Bytes.toString(CellUtil.cloneFamily(cell))
                                +"->"+
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                +"->"+
                                Bytes.toString(CellUtil.cloneValue(cell))
                                +"->"+
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
    }

    public static List<String> listTable() {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            TableName[] tableNames = admin.listTableNames();
            List<String> tblArr = new ArrayList<String>();
            for (int i = 0; i < tableNames.length; i++) {
                tblArr.add(tableNames[i].getNameAsString());
                System.out.println("Table: " + tableNames[i].getNameAsString());
            }
            return tblArr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(System.getenv().get("HADOOP_HOME"));
        HbaseDaoImp hdao=new HbaseDaoImp();
        HTable table = hdao.getTable("aml:trans");
//       Cell[] cells= (table,"900000000021","f1");
//        pirntCells(cells);
        //putData(table);
        //deleteData(table);
//        scanData(table);
        rangeData(table);
    }
}
