package com.pwc.aml.alert.service;

import com.pwc.aml.alert.entity.Alerts;

import java.util.List;

public interface IAlertService {

    public List<Alerts> getAllAlertsData() throws Exception;

}
