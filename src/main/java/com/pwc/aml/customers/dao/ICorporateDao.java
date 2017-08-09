package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Corporate;

import java.util.List;

public interface ICorporateDao {
    void save(Corporate corporate);

    Corporate update(Corporate corporate);
    Corporate findByCustId(String custId);

    void removeAll();

    List<Corporate> findAll();
}
