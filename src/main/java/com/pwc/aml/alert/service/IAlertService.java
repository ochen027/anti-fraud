package com.pwc.aml.alert.service;

import com.pwc.aml.alert.entity.Alerts;

import java.io.IOException;
import java.util.List;

public interface IAlertService {

    List<Alerts> getAllAlertsData() throws Exception;

    Alerts getSingleAlert(String alertId) throws Exception;

    void truncateTable() throws IOException;
}
