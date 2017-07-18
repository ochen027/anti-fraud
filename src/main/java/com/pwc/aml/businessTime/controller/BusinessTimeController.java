package com.pwc.aml.businessTime.controller;

import com.pwc.aml.businessTime.service.IBusinessTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

import java.util.Map;

@Controller
@RequestMapping("businessTime")
public class BusinessTimeController {

    @Autowired
    private IBusinessTimeService businessTimeService;

    @GetMapping("get")
    public ResponseEntity<Map<String,String>> get(){

        Map<String,String> map=new HashMap<>();
        map.put("businessTime",businessTimeService.get());
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }

    @GetMapping("toNextDay")
    public ResponseEntity<Map<String,String>> toNextDay(){
        Map<String,String> map=new HashMap<>();
        businessTimeService.toNextDay();
        map.put("businessTime",businessTimeService.get());
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }




}
