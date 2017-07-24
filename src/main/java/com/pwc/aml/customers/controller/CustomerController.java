package com.pwc.aml.customers.controller;

import com.pwc.aml.customers.entity.Customers;
import com.pwc.aml.customers.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.pwc.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Autowired
    private ICustomerService customerService;


    @PostMapping("saveOrUpdateCustomer")
    public ResponseEntity<Void> saveOrUpdateCustomer(@RequestBody Customers customers) {
        customerService.saveOrUpdateCustomer(customers);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PostMapping("findByCustId")
    public ResponseEntity<Customers> findByCustId(@RequestBody Customers customers) {
        Customers target=  customerService.findByCustId(customers);
        return new ResponseEntity<Customers>(target,HttpStatus.OK);
    }


    @PostMapping("findByCustCtNo")
    public ResponseEntity<Customers> findByCustCtNo(@RequestBody Customers customers) {
        Customers target=  customerService.findByCustCtNo(customers);
        return new ResponseEntity<Customers>(target,HttpStatus.OK);
    }




    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
            customerService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping ("findAll")
    public ResponseEntity<List<Customers>> findAll() {
        List<Customers> targets=  customerService.findAll();
        return new ResponseEntity<List<Customers>>(targets,HttpStatus.OK);
    }

    @GetMapping ("removeAll")
    public ResponseEntity<Void> removeAll() {
        customerService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
