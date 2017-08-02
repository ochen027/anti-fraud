package com.pwc.aml.riskCountry.controller;


import com.pwc.aml.riskCountry.entity.RiskCountry;
import com.pwc.aml.riskCountry.service.IRiskCountryService;
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
@RequestMapping("riskCountry")
public class RiskCountryController  extends BaseController {

    @Autowired
    private IRiskCountryService riskCountryService;


    @GetMapping("getAll")
    public ResponseEntity<List<RiskCountry>> getAll() {

        List<RiskCountry> RiskCountries = riskCountryService.getAllRiskCountry();

        return new ResponseEntity<List<RiskCountry>>(RiskCountries, HttpStatus.OK);
    }


    @GetMapping("getSingleCountry/{id}")
    public ResponseEntity<RiskCountry> getSingleCountry(@PathVariable("id") int id){
        RiskCountry rc = riskCountryService.findSingleCountry(id);
        return new ResponseEntity<RiskCountry>(rc, HttpStatus.OK);
    }

    @GetMapping("deleteCountry/{id}")
    public ResponseEntity<Void> deleteCountry(@RequestBody RiskCountry riskCountry){
        riskCountryService.deleteCountry(riskCountry);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException, ParseException {

        if (!file.isEmpty()) {
            riskCountryService.importCsvData(file);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("removeAll")
    public ResponseEntity<Void> removeAll() {
        riskCountryService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
