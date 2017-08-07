package com.pwc.aml.accounts.service;

import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.customers.entity.Customers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IAccountService {
    void saveOrUpdateAccount(Accounts account);
    
    Accounts findByAcctId(Accounts accounts);

    List<Accounts> findByCustId(Accounts accounts);
   
    List<Accounts> findAll();

    void removeAll();

    void importCsvData(MultipartFile file) throws IOException, ParseException;


}
