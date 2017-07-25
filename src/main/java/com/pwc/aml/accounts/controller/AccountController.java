package com.pwc.aml.accounts.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pwc.aml.accounts.service.IAccountService;
import com.pwc.common.base.controller.BaseController;

@Controller
@RequestMapping("account")
public class AccountController extends BaseController{
    @Autowired
    private IAccountService accountService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
        	accountService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
