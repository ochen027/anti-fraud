package com.pwc.aml.accounts.dao;

import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.customers.entity.Customers;

import java.util.List;

public interface IAccountDAO {

    void save(Accounts accounts);

    Accounts update(Accounts accounts);

    List<Accounts> findByCustId(String custId);

    Accounts findByAcctId(String accountId);

    void removeAll();

    List<Accounts> findAll();
}
