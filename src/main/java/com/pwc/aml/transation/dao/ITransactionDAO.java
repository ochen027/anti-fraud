package com.pwc.aml.transation.dao;

import com.pwc.aml.transation.entity.SearchTransEntity;
import com.pwc.aml.transation.entity.Transactions;

import java.util.List;

public interface ITransactionDAO {

    void importTransData()throws Exception;

    List<Transactions> getAllTransData() throws Exception;

    Transactions getSingleTrans(String transId) throws Exception;

    List<Transactions> getTransListById(String[] transIds) throws Exception;

    void TruncateTrans() throws Exception;

    List<Transactions> getTransDataByAccount(List<String> accountId, String ruleDays, String businessDate) throws Exception;

    Integer getTransByDateCount(String businessDate) throws Exception;

    List<Transactions> getTransDataByAccount(String acctId, String ruleDays, String businessDate) throws Exception;

    List<Transactions> searchTransByCondition(SearchTransEntity ste) throws Exception;
}
