package com.pwc.aml.assign.dao;


import com.pwc.aml.assign.entity.Assign;

import java.util.List;

public interface IAssignDao {

    void save(Assign assign) throws Exception;
    List<Assign> findByUserId(String userId) throws Exception;
}
