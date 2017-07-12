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

    @Autowired
    private IWorkflowService workflowService;


    @GetMapping("getAllWorkflow")
    public ResponseEntity<List<Workflow>> getAllWorkflow() {

        List<Workflow> workflows = workflowService.getAllworkflow();

        return new ResponseEntity<List<Workflow>>(workflows, HttpStatus.OK);
    }

    @PostMapping("saveOrUpdate")
    public ResponseEntity<Void> saveWorkflow(@RequestBody Workflow workflow) {
        workflowService.save(workflow);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
