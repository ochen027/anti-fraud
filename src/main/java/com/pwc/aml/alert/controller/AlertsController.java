package com.pwc.aml.alert.controller;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.alert.service.IAlertService;
import com.pwc.aml.transation.service.ITransactionService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.service.IWorkObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.pwc.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("alerts")
public class AlertsController extends BaseController {

    @Autowired
    private IAlertService alertService;

    @Autowired
    private IWorkObjService workObjService;

    @GetMapping("getAllAlerts")
    public ResponseEntity<List<Alerts>> getAllAlerts() throws  Exception{
        return new ResponseEntity<List<Alerts>>(alertService.getAllAlertsData(), HttpStatus.OK);
    }

    @GetMapping("getSingleAlert/{id}")
    public ResponseEntity<Alerts> getSingleAlert(@PathVariable String id) throws Exception{
        return new ResponseEntity<Alerts>(alertService.getSingleAlert(id), HttpStatus.OK);
    }

    @GetMapping("searchClosedAlert")
    public ResponseEntity<List<WorkObj>> searchClosedAlerts(@RequestBody AlertSearchEntity se) throws Exception{
        return new ResponseEntity<List<WorkObj>>(alertService.searchClosedAlerts(se), HttpStatus.OK);
    }

}
