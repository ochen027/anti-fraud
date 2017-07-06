package com.pwc.aml.transation.controller;

import com.pwc.aml.entity.Article;
import com.pwc.aml.transation.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by aliu323 on 7/5/2017.
 */

@Controller
@RequestMapping("transaction")
public class TransactionRestController {
    @Autowired
    private ITransactionService transactionServiceImp;

    @GetMapping("getSingle/{id}")
    public ResponseEntity<Map<String, String>> getSingle(@PathVariable("id") String id) throws Exception {
        Map<String, String> map=transactionServiceImp.getData("aml:trans",id,"f1");
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

}
