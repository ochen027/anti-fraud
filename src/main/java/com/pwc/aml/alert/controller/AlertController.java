package com.pwc.aml.alert.controller;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.alert.service.IAlertService;
import com.pwc.aml.transation.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.pwc.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("alerts")
public class AlertController extends BaseController {

    @Autowired
    private IAlertService alertService;

    @RequestMapping("getAllAlerts")
    public ResponseEntity<List<Alerts>> getAllAlerts() throws  Exception{
        return new ResponseEntity<List<Alerts>>(alertService.getAllAlertsData(), HttpStatus.OK);
    }

}
