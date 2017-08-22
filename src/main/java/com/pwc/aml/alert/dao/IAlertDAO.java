package com.pwc.aml.alert.dao;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.transation.entity.Transactions;

import java.io.IOException;
import java.util.List;

public interface IAlertDAO {

    Alerts getSingleAlert(String alertId) throws Exception;

    List<Alerts> getAllAlertsData() throws Exception;

    void truncateTable() throws IOException;

    String createByManually(List<Transactions> tList, Scenario scenario, String userName) throws Exception;
}
