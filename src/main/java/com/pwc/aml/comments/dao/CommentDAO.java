package com.pwc.aml.comments.dao;

import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.common.hbase.IHbaseDao;
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
        HTable table = hBaseDAO.getTable("aml:comments");
        hBaseDAO.putData(table, c.getCommentId(), "f1", "commentContents", c.getCommentContents());
        hBaseDAO.putData(table, c.getCommentId(), "f1", "commentCreatedBy", c.getCommentCreatedBy());
        hBaseDAO.putData(table, c.getCommentId(), "f1", "commentCreatedDate", c.getCommentCreatedDate().toString());
        hBaseDAO.putData(table, c.getCommentId(), "f1", "alertId", c.getAlertId());
    }

    @Override
    public void updateComment(Comments c) {
        //TODO
    }

    @Override
    public void removeComment(String commentId) throws Exception{
        HTable table = hBaseDAO.getTable("aml:comments");
        hBaseDAO.deleteData(table, commentId);
    }

    @Override
    public Comments getSingleComment(String commentId) throws Exception{
        HTable table = hBaseDAO.getTable("aml:comments");
        return this.consistComment(hBaseDAO.getData(table, commentId, "f1"));
    }

    @Override
    public List<Comments> getAllComments() throws Exception{
        Scan scan = new Scan();
        HTable hTable = hBaseDAO.getTable("aml:comments");
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
        HTable hTable = hBaseDAO.getTable("aml:comments");
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("f1"), Bytes.toBytes("alertId"),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(alertId));
        scan.setFilter(filter);
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
                case "alertId":
                    cBean.setAlertId(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "commentContents":
                    cBean.setCommentContents(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "commentCreatedDate":
                    cBean.setCommentCreatedDate(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
                case "commentCreatedBy":
                    cBean.setCommentCreatedBy(Bytes.toString(CellUtil.cloneValue(c)));
                    continue;
            }
        }
        return cBean;
    }
}
