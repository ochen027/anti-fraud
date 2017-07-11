package com.pwc.aml.transation.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.transation.service.ITransactionService;

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

    @GetMapping("import")
    public ResponseEntity<String> getSingle() throws Exception {
        transactionServiceImp.importData();
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @GetMapping("truncate")
    public ResponseEntity<String> truncate() throws Exception {
        transactionServiceImp.truncateTable();
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    
    @GetMapping("listAll")
    public ResponseEntity<List<Transactions>> listAll() throws Exception{
		return new ResponseEntity<List<Transactions>>(transactionServiceImp.getAllData("aml:trans", "f1"), HttpStatus.OK);
    }

}
