package com.pwc.aml.customers.service;
import com.pwc.aml.customers.entity.CustomerBase;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ICustomerBaseService {
    public void saveOrUpdateCustomerBase(CustomerBase customerBase);
    CustomerBase findByCustId(CustomerBase customerBase);


    List<CustomerBase> findAll();

    void removeAll();

    void importCsvData(MultipartFile file) throws IOException, ParseException;
}
