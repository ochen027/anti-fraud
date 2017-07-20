package com.pwc.aml.alert.dao;

import com.pwc.aml.alert.entity.Alerts;

import java.util.List;

public interface IAlertDAO {

    Alerts getSingleAlert(String alertId) throws Exception;

    List<Alerts> getAllAlertsData() throws Exception;
}
