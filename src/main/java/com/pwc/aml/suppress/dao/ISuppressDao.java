package com.pwc.aml.suppress.dao;


import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.component.authorize.users.entity.Users;

import java.util.List;

public interface ISuppressDao {


    void createOrUpdate(Suppress suppress) throws Exception;

    List<Suppress> findAll() throws Exception;

    List<Suppress> findAllActive() throws Exception;
}
