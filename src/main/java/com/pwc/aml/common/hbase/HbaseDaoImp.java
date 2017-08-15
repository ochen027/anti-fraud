package com.pwc.aml.common.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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
                Bytes.toBytes(null == value ? "" : value)
        );
        //load the put
        table.put(put);

    }

    public void putData(HTable table, String rowkey, String columnFamily, String column, double value) throws Exception {
        Put put = new Put(Bytes.toBytes(rowkey));
        put.add(
                Bytes.toBytes(columnFamily),
                Bytes.toBytes(column),
                Bytes.toBytes(value)
        );
        table.put(put);
    }

    public void putData(HTable table, String rowkey, String columnFamily, String column, Long value) throws Exception {
        Put put = new Put(Bytes.toBytes(rowkey));
        put.add(
                Bytes.toBytes(columnFamily),
                Bytes.toBytes(column),
                Bytes.toBytes(value)
        );
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
        HTable table = hdao.getTable("aml:trans");
        
        
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



        hdao.scanData(table);


        /**
        hdao.deleteTable("aml:assign");
        hdao.deleteTable("aml:alerts");
        hdao.deleteTable("aml:workObj");
        hdao.deleteTable("aml:documents");
        hdao.deleteTable("aml:comments");

        hdao.createTable("aml:assign");
        hdao.createTable("aml:alerts");
        hdao.createTable("aml:workObj");
        hdao.createTable("aml:documents");
        hdao.createTable("aml:comments");
        **/


        //hdao.createTable("aml:assign");


        table.close();


    }



}
