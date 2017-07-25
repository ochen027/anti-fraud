package com.pwc.aml.transation.dao;

import com.pwc.aml.transation.entity.Transactions;

import java.util.List;

public interface ITransactionDAO {

    void importTransData()throws Exception;

    List<Transactions> getAllTransData() throws Exception;

    Transactions getSingleTrans(String transId) throws Exception;

    void TruncateTrans() throws Exception;

    List<Transactions> getTransDataByAccount(List<String> accountId, String ruleDays, String businessDate) throws Exception;
}
