package com.pwc.aml.suppress.service;

import com.pwc.aml.suppress.dao.ISuppressDao;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SuppressService implements ISuppressService {


    @Autowired
    private ISuppressDao suppressDao;

    @Override
    public void addSuppress(Suppress suppress, Users users) {

    }

    @Override
    public List<Suppress> findAll() throws Exception {
        return suppressDao.findAll();
    }

    @Override
    public void inActiveSuppress(Suppress suppress, Users users) {

    }

    @Override
    public List<Suppress> findAllActive() throws Exception {
        return suppressDao.findAllActive();
    }


}
