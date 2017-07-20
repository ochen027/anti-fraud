package com.pwc.aml.customers.service;

import com.pwc.aml.customers.entity.Customers;

public interface ICustomerService {

    void saveOrUpdateCustomer(Customers customer);

    Customers findByCustId(Customers customer);

    Customers findByCustCtNo(Customers customer);
}
