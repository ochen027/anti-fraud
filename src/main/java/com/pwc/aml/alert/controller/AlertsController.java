package com.pwc.aml.alert.controller;

import com.pwc.aml.alert.entity.AlertCreation;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.alert.service.IAlertService;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.transation.service.ITransactionService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.service.IWorkObjService;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.pwc.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("alerts")
public class AlertsController extends BaseController {

    @Autowired
    IAlertService alertService;

    @GetMapping("getAllAlerts")
    public ResponseEntity<List<Alerts>> getAllAlerts() throws  Exception{
        return new ResponseEntity<List<Alerts>>(alertService.getAllAlertsData(), HttpStatus.OK);
    }

    @GetMapping("getSingleAlert/{id}")
    public ResponseEntity<Alerts> getSingleAlert(@PathVariable String id) throws Exception{
        return new ResponseEntity<Alerts>(alertService.getSingleAlert(id), HttpStatus.OK);
    }

    @PostMapping("searchClosedAlert")
    public ResponseEntity<List<WorkObj>> searchClosedAlerts(@RequestBody AlertSearchEntity se) throws Exception{
        return new ResponseEntity<List<WorkObj>>(alertService.searchClosedAlerts(se), HttpStatus.OK);
    }

    @PostMapping("createAlert")
    public ResponseEntity<Void> createAlertByManually(@RequestBody AlertCreation ac) throws Exception{
        alertService.createAlertByManually(ac.getTransList(), ac.getScenario(), userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping("exportClose")
    public ResponseEntity<Void> exportClose( HttpServletResponse response) throws Exception{
        return alertService.exportClose(response);

    }


}
