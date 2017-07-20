package com.pwc.aml.alert.service;

import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.common.hbase.IHbaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService implements IAlertService{

    @Autowired
    private IHbaseDao hBaseDao;

    @Autowired
    private IAlertDAO alertDAO;

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        return hBaseDao.getAllAlertsData();
    }

    @Override
    public Alerts getSingleAlert(String alertId) throws Exception {
        return alertDAO.getSingleAlert(alertId);
    }
}
