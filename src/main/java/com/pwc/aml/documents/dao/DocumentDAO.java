package com.pwc.aml.documents.dao;

import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.Constants;
import com.pwc.aml.common.util.HdfsAPI;
import com.pwc.aml.documents.entity.Documents;
import com.pwc.common.util.FormatUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Repository
public class DocumentDAO implements IDocumentDAO {

    @Autowired
    private IHbaseDao hBaseDAO;

    @Override
    public void saveFile(String localPath, String HDFSPath) throws Exception{
        HdfsAPI.write(localPath, HDFSPath);
    }

    @Override
    public void create(Documents doc) throws Exception {
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_DOCUMENTS);
        hBaseDAO.putData(table, doc.getDocId(), Constants.F1, Constants.COLUMN_DOCUMENT_FILE_PATH, doc.getDocPath());
        hBaseDAO.putData(table, doc.getDocId(), Constants.F1, Constants.CREATED_BY, doc.getDocCreatedBy());
        hBaseDAO.putData(table, doc.getDocId(), Constants.F1, Constants.CREATED_DATE, doc.getDocCreatedDate());
        hBaseDAO.putData(table, doc.getDocId(), Constants.F1, Constants.COLUMN_WORK_OBJECT_ID, doc.getWorkObjId());

    }

    @Override
    public List<Documents> getDocByAlertId(String workObjId) throws Exception {
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_DOCUMENTS);
        Scan scan = new Scan();
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes(Constants.F1), Bytes.toBytes(Constants.COLUMN_WORK_OBJECT_ID),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(workObjId));
        scan.setFilter(filter);
        ResultScanner rsscan = table.getScanner(scan);
        List<Documents> dList = new ArrayList<Documents>();
        for (Result r : rsscan) {
            Documents doc = this.consistDocument(r.rawCells(), Bytes.toString(r.getRow()));
            dList.add(doc);
        }
        rsscan.close();
        return dList;
    }

    private Documents consistDocument(Cell[] cells, String rowKey) throws ParseException {
        Documents docBean = new Documents();
        docBean.setDocId(rowKey);
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case Constants.COLUMN_WORK_OBJECT_ID:
                    docBean.setWorkObjId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_DOCUMENT_FILE_PATH:
                    docBean.setDocPath(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.CREATED_BY:
                    docBean.setDocCreatedBy(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.CREATED_DATE:
                    docBean.setDocCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
            }

        }
        return docBean;
    }
}
