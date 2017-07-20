package com.pwc.aml.alert.dao;

import com.pwc.aml.alert.entity.Alerts;

public interface IAlertDAO {

    Alerts getSingleAlert(String alertId) throws Exception;

}
