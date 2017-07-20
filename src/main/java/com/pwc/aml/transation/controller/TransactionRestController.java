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
    private ITransactionService transactionService;

    @GetMapping("getSingleTrans/{id}")
    public ResponseEntity<Transactions> getSingleTrans(@PathVariable("id") String id) throws Exception {
        return new ResponseEntity<Transactions>(transactionService.getSingleTrans(id), HttpStatus.OK);
    }

    @GetMapping("importTrans")
    public ResponseEntity<Void> importTrans() throws Exception {
        transactionService.importData();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("listAllTrans")
    public ResponseEntity<List<Transactions>> listAll() throws Exception{
		return new ResponseEntity<List<Transactions>>(transactionService.getAllTransData(), HttpStatus.OK);
    }

    @GetMapping("truncateTrans")
    public ResponseEntity<Void> TruncateTrans() throws Exception {
        transactionService.truncateTrans();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
