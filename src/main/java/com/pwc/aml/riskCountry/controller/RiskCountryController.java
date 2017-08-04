package com.pwc.aml.riskCountry.controller;


import com.pwc.aml.riskCountry.entity.RiskCountry;
import com.pwc.aml.riskCountry.service.IRiskCountryService;
import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.roles.entity.Roles;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @PostMapping("deleteCountry/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") int id){

        riskCountryService.deleteCountry(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody RiskCountry rc){
            riskCountryService.delete(rc);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,HttpSession session) throws IOException, ParseException {


        //assign to me
        Map<String, Object> userInfo =( Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users)userInfo.get("User");

        if (!file.isEmpty()) {
            riskCountryService.importCsvData(file,user.getUserName());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("removeAll")
    public ResponseEntity<Void> removeAll() {
        riskCountryService.removeAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("saveOrUpdate")
    public ResponseEntity<Void> saveOrUpdate(@RequestBody RiskCountry rc){
        riskCountryService.saveOrUpdate(rc);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
