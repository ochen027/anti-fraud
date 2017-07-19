package com.pwc.aml.workflow.controller;

import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.component.workflow.entity.Workflow;
import com.pwc.component.workflow.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("workflow")
public class WorkflowExController {

    @Autowired
    private IWorkflowExService workflowService;


    @PostMapping("saveOrUpdateWithRoles")
    public ResponseEntity<Void> saveWorkflow(@RequestBody WorkflowEx workflow) {
        workflowService.saveOrUpdate(workflow);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
