package com.pwc.aml.riskCountry.dao;

import com.pwc.aml.riskCountry.entity.RiskCountry;

import java.util.List;

public interface IRiskCountryDao {

    public RiskCountry save(RiskCountry riskCountry);

    RiskCountry update(RiskCountry riskCountry);

    void deleteById(int id);
    void delete(RiskCountry rc);
    List<RiskCountry> findAll();

    RiskCountry findById(int id);
    void removeAll();

}
