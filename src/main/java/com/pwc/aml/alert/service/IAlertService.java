package com.pwc.aml.alert.service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.workflow.entity.WorkObj;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IAlertService {

    List<Alerts> getAllAlertsData() throws Exception;

    Alerts getSingleAlert(String alertId) throws Exception;

    void truncateTable() throws IOException;

    List<WorkObj> searchClosedAlerts(AlertSearchEntity ase) throws Exception;

    void createAlertByManually(List<Transactions> tList, Scenario scenario, String userName) throws Exception;
    public ResponseEntity<Void> exportClose( HttpServletResponse response) throws Exception;
}
