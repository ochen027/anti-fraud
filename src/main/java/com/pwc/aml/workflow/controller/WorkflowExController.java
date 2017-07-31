package com.pwc.aml.workflow.controller;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.alert.service.IAlertService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.Workflow;
import com.pwc.component.workflow.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("workflow")
public class WorkflowExController {

    @Autowired
    private IWorkflowExService workflowService;

    @Autowired
    private IAlertService alertService;

    @Autowired
    private IWorkObjService workObjService;



    @PostMapping("saveOrUpdateWithRoles")
    public ResponseEntity<Void> saveWorkflow(@RequestBody WorkflowEx workflow) {
        workflowService.saveOrUpdate(workflow);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @GetMapping("test")
    public ResponseEntity<Void> test() throws Exception {

        Alerts alert=alertService.getSingleAlert("20170728163112212373351046537238");

        WorkflowEx workflowEx=  workflowService.getWorkflowByFlowId("954dc267-c3e2-43d1-abdb-a83ca2881875");

        List<FlowEvent> events= workObjService.attach(alert,workflowEx);

        List<WorkObj> workObjs= workObjService.getWorkObjsByPointId("f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19");

        workObjService.doEvent(workObjs.get(0),events.get(0));


        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("delete")
    public ResponseEntity<Void> delete() throws Exception {

        workObjService.truncateTable();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
