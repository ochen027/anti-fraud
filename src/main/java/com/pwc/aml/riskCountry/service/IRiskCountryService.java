package com.pwc.aml.riskCountry.service;

import com.pwc.aml.riskCountry.entity.RiskCountry;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IRiskCountryService {
    List<RiskCountry> getAllRiskCountry();
    RiskCountry findSingleCountry(int ID);
    void deleteCountry(int id);
    void delete(RiskCountry rc);
    void importCsvData(MultipartFile file,String userName) throws IOException, ParseException;
    void removeAll();
    public RiskCountry saveOrUpdate(RiskCountry rc);
}

