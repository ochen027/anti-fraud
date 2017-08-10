package com.pwc.aml.customers.dao;



import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.customers.entity.Individual;

import java.util.List;

public interface IIndividualDao {
    void save(Individual individual);

    Individual update(Individual individual);
    Individual findByCustId(String custId);

    void removeAll();

    List<Individual> findAll();
}
