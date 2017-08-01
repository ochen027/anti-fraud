package com.pwc.aml.common.hbase;

import com.jcraft.jsch.JSchException;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.documents.entity.Documents;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.common.util.FormatUtils;
import com.pwc.aml.common.util.RunShellTool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.datanucleus.store.rdbms.query.AbstractRDBMSQueryResult;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Repository
public class HbaseDaoImp implements IHbaseDao {


    static final Configuration conf = HBaseConfiguration.create();
    static Properties pro = new Properties();
    static HConnection connection;

    static {
        try {
            connection = HConnectionManager.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public HbaseDaoImp() throws IOException {
//
//        FileInputStream fis=new FileInputStream(new File("application.properties"));
//        this.pro.load(fis);
//
//    }


    public void deleteTable(String tableName) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
        System.out.println("Table deleted");
    }

    public void createTable(String tableName) throws IOException {
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(conf);
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
            HColumnDescriptor family = new HColumnDescriptor(Bytes.toBytes("f1"));
            // 开启列簇 -- store的块缓存
            family.setBlockCacheEnabled(true);
            family.setBlocksize(1024 * 1024 * 2);
            family.setMaxVersions(1);
            family.setMinVersions(1);
            desc.addFamily(family);

            //admin.createTable(desc);
//            byte[][] splitKeys = {
//                    Bytes.toBytes("100"),
//                    Bytes.toBytes("200"),
//                    Bytes.toBytes("300")
//            };
//            admin.createTable(desc,splitKeys);
            admin.createTable(desc);


        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (admin != null)
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public HTable getTable(String name) throws Exception {

        //get the hbase table instance
        HTable table = (HTable) connection.getTable(name);
//        HTable table = new HTable(conf, name);
        System.out.println(System.currentTimeMillis());
        return table;
    }

    /**
     * put the data to the hbase table
     * <p>
     * put :
     * put 'tbname','rowkey','cf:col','value'
     *
     * @throws Exception
     */
    public void putData(HTable table, String rowkey, String columnFamily, String column, String value) throws Exception {
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
     * <p>
     * delete :
     * delete 'tbname','rowkey','cf:col'
     *
     * @throws Exception
     */
    public void deleteData(HTable table, String rowkey, String columnFamily, String column) throws Exception {
        //get the delete instance
        Delete del = new Delete(Bytes.toBytes(rowkey));
        //conf the del
        //del.deleteColumn(Bytes.toBytes("info"),Bytes.toBytes("age"));
        //deleteColumns就是删除所有version
        del.deleteColumns(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        //load the del
        table.delete(del);
    }

    public void deleteData(HTable table, String rowkey) throws Exception {
        Delete del = new Delete(Bytes.toBytes(rowkey));
        table.delete(del);
    }

    //delete by columnFamily
    public void deleteByColumnFamily(HTable table, String rowkey, String columnFamily) throws Exception {
        //get the delete instance
        Delete del = new Delete(Bytes.toBytes(rowkey));
        del.deleteFamily(Bytes.toBytes(columnFamily));
        table.delete(del);

    }

    /**
     * get the data from the hbase table
     * <p>
     * get :
     * get 'tbname','rowkey','cf:col'
     *
     * @throws Exception
     */
    public Cell[] getData(HTable table, String rowKey, String columnFamily) throws Exception {
        // TODO Auto-generated method stub
        Get get = new Get(Bytes.toBytes(rowKey));
        //conf the get
        //get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));

        get.addFamily(Bytes.toBytes(columnFamily));
        //load the get
        Result rs = table.get(get);
        //print the data
        Cell[] cells = rs.rawCells();
        return cells;
    }
//    public Cell getData(HTable table, String rowKey, String columnFamily,String column) throws Exception {
//        // TODO Auto-generated method stub
//        Get get = new Get(Bytes.toBytes(rowKey));
//        //conf the get
//        //get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
//
//        get.addFamily(Bytes.toBytes(columnFamily));
//        get.addColumn(Bytes.toBytes( " "));
//        //load the get
//        Result rs = table.get(get);
//        //print the data
//        rs.r
//        return Cell;
//    }



    /**
     * scan the all table
     * scan :
     * scan 'tbname'
     */
    public static void scanData(HTable table) throws Exception {
        //get the scan instance
        Scan scan = new Scan();

        //load the scan
        ResultScanner rsscan = table.getScanner(scan);
        for (Result rs : rsscan) {
            System.out.println(Bytes.toString(rs.getRow()));
            for (Cell cell : rs.rawCells()) {
                System.out.println(
                        Bytes.toString(CellUtil.cloneFamily(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneValue(cell))
                                + "->" +
                                cell.getTimestamp()
                );
            }
            rsscan.close();
            System.out.println("------------------------------");
        }
    }



    //pringt cells
    public static void pirntCells(Cell[] cells) {
        //rs.cells（）获得cells数组
        for (Cell cell : cells) {
            System.out.println(
                    //用cellUtil来获取cell的值
                    Bytes.toString(CellUtil.cloneFamily(cell))
                            + "->" +
                            Bytes.toString(CellUtil.cloneQualifier(cell))
                            + "->" +
                            Bytes.toString(CellUtil.cloneValue(cell))
                            + "->" +
                            cell.getTimestamp()
            );
            System.out.println("------------------------------");
        }
    }

    /**
     * scan the table  with limit
     * * scan :
     * scan 'tbname',{STARTROW => 'row1',STOPROW => 'row2'}
     */
    public static void rangeData(HTable table) throws Exception {
        //get the scan instance
        Scan scan = new Scan();
        //conf the scan
        //scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
        //scan.addFamily(family);
        //scan.setStartRow(Bytes.toBytes("20161119_10002"));
        //scan.setStopRow(Bytes.toBytes("20161119_10003"));
        Filter filter = new PrefixFilter(Bytes.toBytes("900000000001"));
//        scan.set
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
        for (Result rs : rsscan) {
            System.out.println(Bytes.toString(rs.getRow()));
            for (Cell cell : rs.rawCells()) {
                System.out.println(
                        Bytes.toString(CellUtil.cloneFamily(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneValue(cell))
                                + "->" +
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
    }

    public static void getDataByColumn() throws Exception{
        HbaseDaoImp hdao = new HbaseDaoImp();
        HTable table = hdao.getTable("aml:trans");
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                Bytes.toBytes("acct_id"),
                CompareFilter.CompareOp.EQUAL,Bytes.toBytes("acct0000001")
        ));

        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                Bytes.toBytes("acct_id"),
                CompareFilter.CompareOp.EQUAL,Bytes.toBytes("acct0000002")
        ));
        /**
        filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes("f1"),
                Bytes.toBytes("as_of_date"),
                CompareFilter.CompareOp.EQUAL,Bytes.toBytes("20100801")
        ));
        **/
        scan.setFilter(filterList);
        scan.setStartRow(Bytes.toBytes("20080101"));
        scan.setStopRow(Bytes.toBytes("20080203"));
        ResultScanner rsscan = table.getScanner(scan);
        for (Result r : rsscan) {
            System.out.println(Bytes.toString(r.getRow()));
            for (Cell cell : r.rawCells()) {
                System.out.println(
                        Bytes.toString(CellUtil.cloneFamily(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneValue(cell))
                                + "->" +
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
        rsscan.close();


    }



    public static void getDataByColumn(HTable table, String column, String columnValue) throws Exception{
        Scan scan = new Scan();
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("f1"), Bytes.toBytes(column),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(columnValue));
        scan.setFilter(filter);
        ResultScanner rsscan = table.getScanner(scan);
        for (Result r : rsscan) {
            System.out.println(Bytes.toString(r.getRow()));
            for (Cell cell : r.rawCells()) {
                System.out.println(
                        Bytes.toString(CellUtil.cloneFamily(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneValue(cell))
                                + "->" +
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
        rsscan.close();
    }



    public static void main(String[] args) throws Exception {
//        System.out.println(System.getenv().get("HADOOP_HOME"));
        HbaseDaoImp hdao = new HbaseDaoImp();
        HTable table = hdao.getTable("aml:workObj");
        
        
        //hdao.deleteByColumnFamily(table, "trans_id", "f1");
        
//        Cell[] cells= (table,"900000000021","f1");
//        pirntCells(cells);
        //hdao.putData(table, "10000000001", "f1", "alertId", "ALT900000000001");
        //hdao.putData(table, "10000000001", "f1", "", "ALT900000000001");


        //deleteData(table);
        //scanData(table);
        //rangeData(table);
//        String proStr=pro.getProperty( "spring.jpa.generate-ddl");
//
//        System.out.println(proStr);
//          hdao.importTsv();



        //hdao.getDataByColumn(table, "totalAmt", "19999");
        //hdao.getDataByColumn();

        //hdao.createTable("aml:alerts");

        /**
        hdao.putData(table, "ALT900000000001", "f1", "alertName", "Warning");
        hdao.putData(table, "ALT900000000001", "f1", "alertContents", "ALT900000000001->Warning->More Than 10000");
        hdao.putData(table, "ALT900000000001", "f1", "transId", "900000000001,900000000002,900000000004");
        hdao.putData(table, "ALT900000000001", "f1", "customerId", "CUST90001");
        hdao.putData(table, "ALT900000000001", "f1", "accountId", "acct0000001");
        hdao.putData(table, "ALT900000000001", "f1", "scenarioId", "1");
        hdao.putData(table, "ALT900000000001", "f1", "documentId", "DOC90001,DOC90002");
        hdao.putData(table, "ALT900000000001", "f1", "commentId", "COMT90001,COMT90002");
        hdao.putData(table, "ALT900000000001", "f1", "businessDate", "2017-07-01");
        hdao.putData(table, "ALT900000000001", "f1", "totalAmt", "19999");
        hdao.putData(table, "ALT900000000001", "f1", "createdBy", "sysadmin");
        hdao.putData(table, "ALT900000000001", "f1", "createdDate", "2017-07-20");
        hdao.putData(table, "ALT900000000001", "f1", "alertDesc", "Alert1Desc");


        hdao.putData(table, "ALT900000000002", "f1", "alertName", "Error");
        hdao.putData(table, "ALT900000000002", "f1", "alertContents", "ALT900000000002->Error->More Than 50000");
        hdao.putData(table, "ALT900000000002", "f1", "transId", "900000000008,900000000009,900000000010");
        hdao.putData(table, "ALT900000000002", "f1", "customerId", "CUST90002");
        hdao.putData(table, "ALT900000000002", "f1", "accountId", "acct0000002");
        hdao.putData(table, "ALT900000000002", "f1", "scenarioId", "1");
        hdao.putData(table, "ALT900000000002", "f1", "documentId", "DOC90003,DOC90004");
        hdao.putData(table, "ALT900000000002", "f1", "commentId", "COMT90003,COMT90004");
        hdao.putData(table, "ALT900000000002", "f1", "businessDate", "2017-07-01");
        hdao.putData(table, "ALT900000000002", "f1", "totalAmt", "29999");
        hdao.putData(table, "ALT900000000002", "f1", "createdBy", "sysadmin");
        hdao.putData(table, "ALT900000000002", "f1", "createdDate", "2017-07-20");
        hdao.putData(table, "ALT900000000002", "f1", "alertDesc", "Alert2Desc");

        hdao.putData(table, "ALT900000000003", "f1", "alertName", "Info");
        hdao.putData(table, "ALT900000000003", "f1", "alertContents", "ALT900000000003->Info->less Than 10000");
        hdao.putData(table, "ALT900000000003", "f1", "transId", "900000000019,900000000018,900000000003");
        hdao.putData(table, "ALT900000000003", "f1", "customerId", "CUST90003");
        hdao.putData(table, "ALT900000000003", "f1", "accountId", "acct0000004");
        hdao.putData(table, "ALT900000000003", "f1", "scenarioId", "1");
        hdao.putData(table, "ALT900000000003", "f1", "documentId", "DOC90005,DOC90006");
        hdao.putData(table, "ALT900000000003", "f1", "commentId", "COMT90005,COMT90006");
        hdao.putData(table, "ALT900000000003", "f1", "businessDate", "2017-07-01");
        hdao.putData(table, "ALT900000000003", "f1", "totalAmt", "39999");
        hdao.putData(table, "ALT900000000003", "f1", "createdBy", "sysadmin");
        hdao.putData(table, "ALT900000000003", "f1", "createdDate", "2017-07-20");
        hdao.putData(table, "ALT900000000003", "f1", "alertDesc", "Alert3Desc");


        hdao.putData(table, "0000001", "f1", "alertName", "Scenario 1 Conflict");
        hdao.putData(table, "0000001", "f1", "alertContents", "Conflict with Transactions Amount > 30000 and in the last 3 days");
        hdao.putData(table, "0000001", "f1", "scenarioId", "1");
        hdao.putData(table, "0000001", "f1", "alertDesc", "Alert Desc");
         **/


        //hdao.scanData(table);

        hdao.deleteTable("aml:alerts");
        hdao.createTable("aml:alerts");


        table.close();


    }



}
