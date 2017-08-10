package com.pwc.aml.customers.service;

import com.pwc.aml.customers.entity.Corporate;
import com.pwc.aml.customers.entity.Representative;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IRepresentativeService {
    Representative findByCustId(Representative representative);


    List<Representative> findAll();

    void removeAll();

    void importCsvData(MultipartFile file) throws IOException, ParseException;
}
