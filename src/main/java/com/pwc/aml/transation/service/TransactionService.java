package com.pwc.aml.transation.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.CustomerBase;
import com.pwc.aml.transation.dao.ITransactionDAO;
import com.pwc.aml.transation.entity.SearchTransEntity;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    ICustomerBaseDao customerBaseDAO;

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

    @Override
    public List<Transactions> searchTrans(SearchTransEntity ste) throws Exception {
        if(StringUtils.isNotEmpty(ste.getCustomerId()) || StringUtils.isNotEmpty(ste.getCustomerName())){
            List<String> cIdList = customerBaseDAO.findByIdAndName(ste.getCustomerId(), ste.getCustomerName());
            ste.setCustomerIdList(cIdList);
        }
        List<Transactions> tList = transactionDAO.searchTransByCondition(ste);
        List<Transactions> resultList = new ArrayList<Transactions>(tList.size());
        for(Transactions trans : tList){
            String accountId = trans.getAcctId();
            CustomerBase custBase =  customerBaseDAO.findByAccountId(accountId);
            trans.setCustomerId(custBase.getCustomerId());
            trans.setCustomerName(custBase.getCustomerFullName());
            resultList.add(trans);
        }
        return resultList;
    }


}
