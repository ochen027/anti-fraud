package com.pwc.aml.suppress.service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.component.authorize.users.entity.Users;

import java.util.List;

/**
 * Created by whuang072 on 8/15/2017.
 */
public interface ISuppressService {

    void addSuppress(Suppress suppress, Users users) throws Exception;

    List<Suppress> findAll() throws Exception;
    List<Suppress> findSuppress(AlertSearchEntity ase) throws Exception;

    void inActiveSuppress(Suppress suppress, Users users) throws Exception;

    List<Suppress> findAllActive() throws Exception;
}
