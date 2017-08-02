package com.pwc.aml.riskCountry.dao;

import com.pwc.aml.riskCountry.entity.RiskCountry;

import java.util.List;

public interface IRiskCountryDao {

    public void save(RiskCountry riskCountry);

    RiskCountry update(RiskCountry riskCountry);

    void delete(RiskCountry riskCountry);

    List<RiskCountry> findAll();

    RiskCountry findById(int id);
    void removeAll();

}
