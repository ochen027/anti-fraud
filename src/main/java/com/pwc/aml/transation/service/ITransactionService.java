package com.pwc.aml.transation.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.transation.entity.Transactions;

/**
 * Created by aliu323 on 7/4/2017.
 */
public interface ITransactionService {

    Transactions getSingleTrans(String rowKey) throws Exception;

    void importData()throws Exception;
    
    List<Transactions> getAllTransData() throws Exception;

}
