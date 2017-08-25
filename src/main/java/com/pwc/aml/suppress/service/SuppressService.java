package com.pwc.aml.suppress.service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.suppress.dao.ISuppressDao;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class SuppressService implements ISuppressService {


    @Autowired
    private ISuppressDao suppressDao;

    @Override
    public void addSuppress(Suppress suppress, Users users) throws Exception {
        suppress.setSuppressId(UUID.randomUUID().toString());
        suppress.setCreatedBy(users.getUserName());
        suppress.setCreationDate(FormatUtils.getCurrentDay());
        suppress.setLastUpdatedBy(users.getUserName());
        suppress.setLastUpdateDate(FormatUtils.getCurrentDay());
        suppress.setStatus(true);
        suppressDao.createOrUpdate(suppress);
    }

    @Override
    public List<Suppress> findAll() throws Exception {
        return suppressDao.findAll();
    }
    @Override
    public List<Suppress> findSuppress(AlertSearchEntity ase) throws Exception {
        return suppressDao.findSuppress(ase);
    }

    @Override
    public void inActiveSuppress(Suppress suppress, Users users) throws Exception {
        suppress.setStatus(false);
        suppress.setLastUpdatedBy(users.getUserName());
        suppress.setLastUpdateDate(FormatUtils.getCurrentDay());
        suppressDao.createOrUpdate(suppress);
    }

    @Override
    public List<Suppress> findAllActive() throws Exception {
        return suppressDao.findAllActive();
    }


}
