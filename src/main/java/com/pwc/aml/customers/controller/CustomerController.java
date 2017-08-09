package com.pwc.aml.customers.controller;

import com.pwc.aml.customers.entity.*;
import com.pwc.aml.customers.service.*;
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
    @Autowired
    private ICustomerBaseService customerBaseService;
    @Autowired
    private IIndividualService individualService;
    @Autowired
    private ICorporateService corporateService;
    @Autowired
    private IRepresentativeService representativeService;


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
    @RequestMapping(value = "/uploadCustomerBaseFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadCustomerBaseFiles(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
            customerBaseService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/uploadIndividualFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadIndividualFiles(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
            individualService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/uploadCorporateFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadCorporateFiles(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
            corporateService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/uploadRepresentativeFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadRepresentativeFiles(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
            representativeService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping ("findAll")
    public ResponseEntity<List<Customers>> findAll() {
        List<Customers> targets=  customerService.findAll();
        return new ResponseEntity<List<Customers>>(targets,HttpStatus.OK);
    }
    @GetMapping ("findAllCustomerBase")
    public ResponseEntity<List<CustomerBase>> findAllCustomerBase() {
        List<CustomerBase> targets=  customerBaseService.findAll();
        return new ResponseEntity<List<CustomerBase>>(targets,HttpStatus.OK);
    }
    @GetMapping ("findAllIndividual")
    public ResponseEntity<List<Individual>> findAllIndividual() {
        List<Individual> targets=  individualService.findAll();
        return new ResponseEntity<List<Individual>>(targets,HttpStatus.OK);
    }
    @GetMapping ("findAllCorporate")
    public ResponseEntity<List<Corporate>> findAllCorporate() {
        List<Corporate> targets=  corporateService.findAll();
        return new ResponseEntity<List<Corporate>>(targets,HttpStatus.OK);
    }
    @GetMapping ("findAllRepresentative")
    public ResponseEntity<List<Representative>> findAllRepresentative() {
        List<Representative> targets=  representativeService.findAll();
        return new ResponseEntity<List<Representative>>(targets,HttpStatus.OK);
    }

    @GetMapping ("removeAll")
    public ResponseEntity<Void> removeAll() {
        customerService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping ("removeAllCustomerBase")
    public ResponseEntity<Void> removeAllCustomerBase() {
        customerBaseService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping ("removeAllIndividual")
    public ResponseEntity<Void> removeAllIndividual() {
        individualService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping ("removeAllCorporate")
    public ResponseEntity<Void> removeAllCorporate() {
        corporateService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping ("removeAllRepresentative")
    public ResponseEntity<Void> removeAllRepresentative() {
        representativeService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
