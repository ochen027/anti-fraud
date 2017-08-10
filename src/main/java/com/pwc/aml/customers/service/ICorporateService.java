package com.pwc.aml.customers.service;

import com.pwc.aml.customers.entity.Corporate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ICorporateService {
    Corporate findByCustId(Corporate corporate);


    List<Corporate> findAll();

    void removeAll();

    void importCsvData(MultipartFile file) throws IOException, ParseException;
}
