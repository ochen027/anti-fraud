package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.CustomerBase;

import java.util.List;

public interface ICustomerBaseDao {


    void save(CustomerBase customerBase);

    CustomerBase update(CustomerBase customerBase);

    CustomerBase findByCustId(String custId);

    CustomerBase findByCustCtNo(String CtNo);

    void removeAll();

    List<CustomerBase> findAll();


    List<String> findByIdAndName(String customerId, String customerName);

    CustomerBase findByAccountId(String accountId);
}
