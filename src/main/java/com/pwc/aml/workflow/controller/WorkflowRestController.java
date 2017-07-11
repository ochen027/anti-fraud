package com.pwc.aml.workflow.controller;


import com.pwc.aml.workflow.entity.Workflow;
import com.pwc.aml.workflow.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("workflow")
public class WorkflowRestController {


//    @PostMapping("get")
//    public ResponseEntity<Void> get(@RequestBody Workflow u){
//
//    	return new ResponseEntity<Void>(HttpStatus.OK);
//    }

    @GetMapping("getAllWorkflow")
    public ResponseEntity<List<Workflow>> getAllWorkflow(){

        List<Workflow> workflows=new ArrayList<Workflow>();

        return new ResponseEntity<List<Workflow>>(workflows,HttpStatus.OK);
    }

    
}
