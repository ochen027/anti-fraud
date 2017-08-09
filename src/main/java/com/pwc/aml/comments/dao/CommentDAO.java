package com.pwc.aml.comments.dao;

import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.common.util.Constants;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDAO implements ICommentDAO{

    @Autowired
    private IHbaseDao hBaseDAO;


    @Override
    public void createComment(Comments c) throws Exception{
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_COMMENTS);
        c.setCommentId(FormatUtils.GenerateId());
        hBaseDAO.putData(table, c.getCommentId(), "f1", Constants.COLUMN_COMMENTS_CONTENTS, c.getCommentContents());
        hBaseDAO.putData(table, c.getCommentId(), "f1", Constants.COLUMN_COMMENTS_CREATED_BY, c.getCommentCreatedBy());
        hBaseDAO.putData(table, c.getCommentId(), "f1", Constants.COLUMN_COMMENTS_CREATED_DATE, c.getCommentCreatedDate().toString());
        hBaseDAO.putData(table, c.getCommentId(), "f1", Constants.COLUMN_ALERT_ID, c.getAlertId());
    }


    @Override
    public Comments getSingleComment(String commentId) throws Exception{
        HTable table = hBaseDAO.getTable(Constants.HBASE_TABLE_COMMENTS);
        return this.consistComment(hBaseDAO.getData(table, commentId, "f1"));
    }

    @Override
    public List<Comments> getAllComments() throws Exception{
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable(Constants.HBASE_TABLE_COMMENTS);
        ResultScanner rsscan = hTable.getScanner(scan);
        List<Comments> list = new ArrayList<Comments>();
        for (Result rs : rsscan) {
            Comments aBean = this.consistComment(rs.rawCells());
            list.add(aBean);
        }
        return list;
    }

    @Override
    public List<Comments> getCommentsListByAlert(String alertId) throws Exception {
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable(Constants.HBASE_TABLE_COMMENTS);
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("f1"), Bytes.toBytes("alertId"),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(alertId));
        scan.setFilter(filter);
        scan.setReversed(false);
        List<Comments> cList = new ArrayList<Comments>();
        ResultScanner rsscan = hTable.getScanner(scan);
        for (Result rs : rsscan) {
            Comments aBean = this.consistComment(rs.rawCells());
            cList.add(aBean);
        }
        return cList;
    }


    private Comments consistComment(Cell[] cells){
        Comments cBean = new Comments();
        for (Cell c : cells) {
            String key = Bytes.toString(CellUtil.cloneQualifier(c));
            switch(key){
                case Constants.COLUMN_ALERT_ID:
                    cBean.setAlertId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_COMMENTS_CONTENTS:
                    cBean.setCommentContents(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_COMMENTS_CREATED_DATE:
                    cBean.setCommentCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case Constants.COLUMN_COMMENTS_CREATED_BY:
                    cBean.setCommentCreatedBy(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
            }
        }
        return cBean;
    }
}
