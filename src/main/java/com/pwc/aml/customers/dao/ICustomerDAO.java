package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Customers;

import java.util.List;

public interface ICustomerDAO {


    void save(Customers customers);

    Customers update(Customers customers);

    Customers findByCustId(String custId);

    Customers findByCustCtNo(String CtNo);

    void removeAll();

    List<Customers> findAll();

    List<String> findByIdAndName(String customerId, String customerName);
}
