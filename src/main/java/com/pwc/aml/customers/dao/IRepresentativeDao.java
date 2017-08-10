package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Representative;

import java.util.List;

public interface IRepresentativeDao {
    void save(Representative representative);

    Representative update(Representative representative);
    Representative findByCustId(String custId);

    void removeAll();

    List<Representative> findAll();
}
