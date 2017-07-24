package com.pwc.aml.customers.service;

import com.pwc.aml.customers.entity.Customers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ICustomerService {

    void saveOrUpdateCustomer(Customers customer);

    Customers findByCustId(Customers customer);

    Customers findByCustCtNo(Customers customer);

    List<Customers> findAll();

    void removeAll();

    void importCsvData(MultipartFile file) throws IOException, ParseException;
}
