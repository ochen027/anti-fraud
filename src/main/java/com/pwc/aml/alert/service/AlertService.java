package com.pwc.aml.alert.service;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.transation.dao.IHbaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService implements IAlertService{

    @Autowired
    private IHbaseDao hBaseDao;

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        return hBaseDao.getAllAlertsData();
    }
}
