package com.pwc.aml.accounts.controller;

import com.pwc.aml.accounts.entity.Accounts;
import com.pwc.aml.accounts.service.IAccountService;
import com.pwc.aml.customers.entity.Customers;
import com.pwc.common.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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

    @GetMapping("findAll")
    public ResponseEntity<List<Accounts>> findAll() {
        List<Accounts> targets=  accountService.findAll();
        return new ResponseEntity<List<Accounts>>(targets,HttpStatus.OK);
    }

    @GetMapping ("removeAll")
    public ResponseEntity<Void> removeAll() {
        accountService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
