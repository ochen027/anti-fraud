package com.pwc.aml.customers.dao;

import com.pwc.aml.customers.entity.Corporate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public class CorporateDao implements ICorporateDao {
    @Override
    public void save(Corporate corporate) {

    }

    @Override
    public Corporate update(Corporate corporate) {
        return null;
    }

    @Override
    public Corporate findByCustId(String custId) {
        return null;
    }

    @Override
    public void removeAll() {

    }

    @Override
    public List<Corporate> findAll() {
        return null;
    }
}
