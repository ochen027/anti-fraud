package com.pwc.aml.transation.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pwc.aml.transation.dao.ITransactionDAO;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.common.hbase.IHbaseDao;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.transation.service.ITransactionService;

/**
 * Created by aliu323 on 7/4/2017.
 */
@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionDAO transactionDAO;

    @Override
    public Transactions getSingleTrans(String rowKey) throws Exception {
        return transactionDAO.getSingleTrans(rowKey);
    }


    @Override
    public void importData() throws Exception {
        transactionDAO.importTransData();
    }

	@Override
	public List<Transactions> getAllTransData() throws Exception {
		return transactionDAO.getAllTransData();
	}

    @Override
    public void truncateTrans() throws Exception {
        transactionDAO.TruncateTrans();
    }


}
