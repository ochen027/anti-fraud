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
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
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

    
	@Override
	public List<Transactions> getAllTransData() throws Exception {
		Scan scan = new Scan();
        HTable hTable = this.getTable("aml:trans");
    	ResultScanner rsscan = hTable.getScanner(scan);
    	List<Transactions> list = new ArrayList<Transactions>();
    	for (Result rs : rsscan) {
    		Transactions tbean = new Transactions();
    		//System.out.println(Bytes.toString(rs.getRow()));
    		tbean.setTransId(Bytes.toString(rs.getRow()));
    		
    		for (Cell c : rs.rawCells()) {
    			String key = Bytes.toString(CellUtil.cloneQualifier(c));
    			switch(key){
    			
    			case "acct_id":
    				tbean.setAcctId(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "as_of_date":
    				tbean.setAsOfDate(FormatUtils.StringToDate(Bytes.toString(CellUtil.cloneValue(c))));
    				continue;
    			case "counterparty_id_1":
    				tbean.setCounterPartyId(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "currency_cd":
    				tbean.setCurrencyCd(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "trans_base_amt":
    				tbean.setTransBaseAmt(Double.valueOf(Bytes.toString(CellUtil.cloneValue(c))));
    				continue;
    			case "trans_br":
    				tbean.setTransBr(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "trans_by":
    				tbean.setTransBy(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "trans_cdt_cd":
    				tbean.setTransCdtCd(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "trans_chanel":
    				tbean.setTransChanel(Integer.parseInt(Bytes.toString(CellUtil.cloneValue(c))));
    				continue;
    			case "trans_desc":
    				tbean.setTransDesc(Bytes.toString(CellUtil.cloneValue(c)));
    				continue;
    			case "trans_dt":
    				tbean.setTransDt(FormatUtils.StringToDate(Bytes.toString(CellUtil.cloneValue(c))));
    				continue;
    			case "trans_seq":
    				tbean.setTransSeq(Integer.parseInt(Bytes.toString(CellUtil.cloneValue(c))));
    				continue;
    			}
    			
    		}
    		list.add(tbean);
    	}
    	return list;
	}

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        Scan scan = new Scan();
        HTable hTable = this.getTable("aml:alerts");
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Alerts> list = new ArrayList<Alerts>();
        for (Result rs : rsscan) {
            Alerts aBean = new Alerts();
            aBean.setAlterId(Bytes.toString(rs.getRow()));
            for (Cell c : rs.rawCells()) {
                String key = Bytes.toString(CellUtil.cloneQualifier(c));
                switch(key){
                    case "accountId":
                        aBean.setAccountId(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "alertContents":
                        aBean.setAlertContents(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "alertCreatedDate":
                        aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "alertDesc":
                        aBean.setAlertDesc(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "alertName":
                        aBean.setAlertName(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "businessDate":
                        aBean.setBusinessDate(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "commentId":
                        String commentArray = Bytes.toString(CellUtil.cloneValue(c));
                        Comments c1 = new Comments();
                        c1.setCommentId("COMT90001");
                        c1.setAlertId(aBean.getAlterId());
                        c1.setCommentContents("COMT90001");
                        Comments c2 = new Comments();
                        c2.setCommentId("COMT90002");
                        c2.setAlertId(aBean.getAlterId());
                        c2.setCommentContents("COMT90002");
                        List<Comments> cList = new ArrayList<Comments>();
                        cList.add(c1);
                        cList.add(c2);
                        aBean.setCommentsList(cList);
                        continue;
                    case "createdBy":
                        aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "createdDate":
                        aBean.setCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "customerId":
                        aBean.setCustomerId(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "documentId":
                        String docArray = Bytes.toString(CellUtil.cloneValue(c));
                        Documents doc1 = new Documents();
                        doc1.setAlertId(aBean.getAlterId());
                        doc1.setDocId("DOC0001");
                        doc1.setDocPath("/xxx/doc0001");
                        Documents doc2 = new Documents();
                        doc2.setAlertId(aBean.getAlterId());
                        doc2.setDocId("DOC0001");
                        doc2.setDocPath("/xxx/doc0001");
                        List<Documents> dList = new ArrayList<Documents>();
                        dList.add(doc1);
                        dList.add(doc2);
                        aBean.setDocList(dList);
                        continue;
                    case "scenarioId":
                        aBean.setScenarioId(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "totalAmt":
                        aBean.setTotalAmt(Bytes.toString(CellUtil.cloneValue(c)));
                        continue;
                    case "transId":
                        String transArray = Bytes.toString(CellUtil.cloneValue(c));
                        Transactions t1 = new Transactions();
                        t1.setTransId("900000000001");
                        t1.setTransBaseAmt(90000d);

                        Transactions t2 = new Transactions();
                        t2.setTransId("900000000002");
                        t2.setTransBaseAmt(20000d);

                        Transactions t3 = new Transactions();
                        t3.setTransId("900000000003");
                        t3.setTransBaseAmt(30000d);

                        List<Transactions> tList = new ArrayList<Transactions>();
                        tList.add(t1);
                        tList.add(t2);
                        tList.add(t3);
                        aBean.setTransList(tList);
                        continue;

                }

            }
            list.add(aBean);
        }
        return list;
    }


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
        Filter filter = new PrefixFilter(Bytes.toBytes("9000000000"));
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

    public void importTsv() {
        RunShellTool tool = new RunShellTool("172.27.69.76", "hadoop", "Welcome1", 22, "utf-8");
        String result = null;
		try {
			result = tool.execSSH("../../../data/Hadoop/cdh/hadoop-2.5.0-cdh5.3.6/bin/yarn jar ../../data/Hadoop/cdh/hbase-0.98.6-hadoop2/lib/hbase-server-0.98.6-hadoop2.jar importtsv -Dimporttsv.columns=HBASE_ROW_KEY,f1:trans_id,f1:as_of_date,f1:acct_id,f1:trans_seq,f1:trans_chanel,f1:trans_cdt_cd,f1:currency_cd,f1:trans_base_amt,f1:trans_desc,f1:trans_dt,f1:counterparty_id_1,f1:trans_br,f1:trans_by aml:trans /user/hadoop/tmp/sampleData/tsvImport/trans_date");
		} catch (IOException | JSchException e) {
			e.printStackTrace();
		}
        System.out.print(result);
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(System.getenv().get("HADOOP_HOME"));
        HbaseDaoImp hdao = new HbaseDaoImp();
        HTable table = hdao.getTable("aml:alerts");
        
        
        //hdao.deleteByColumnFamily(table, "trans_id", "f1");
        
//        Cell[] cells= (table,"900000000021","f1");
//        pirntCells(cells);
        //hdao.putData(table, "10000000001", "f1", "alertId", "ALT900000000001");
        //hdao.putData(table, "10000000001", "f1", "", "ALT900000000001");


        //deleteData(table);
//        scanData(table);
//        rangeData(table);
//        String proStr=pro.getProperty( "spring.jpa.generate-ddl");
//
//        System.out.println(proStr);
//          hdao.importTsv();

        hdao.scanData(table);


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
        **/




        //hdao.deleteTable("aml:alerts");
        //hdao.createTable("aml:alerts");
    }



}
