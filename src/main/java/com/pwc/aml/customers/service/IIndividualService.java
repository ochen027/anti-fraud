package com.pwc.aml.customers.service;

import com.pwc.aml.customers.entity.Individual;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IIndividualService {
    void saveOrUpdateIndividual(Individual individual);


    List<Individual> findAll();

    void removeAll();

    void importCsvData(MultipartFile file) throws IOException, ParseException;
}
