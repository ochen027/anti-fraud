package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Customers;

public interface ICustomerDAO {


    void save(Customers customers);

    Customers update(Customers customers);

    Customers findByCustId(String custId);

    Customers findByCustCtNo(String CtNo);
}
