package com.pwc.aml.workflow.controller;

import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.alert.service.IAlertService;
import com.pwc.aml.assign.entity.Assign;
import com.pwc.aml.assign.service.IAssignService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("workflow")
public class WorkflowExController extends BaseController{

    @Autowired
    private IWorkflowExService workflowExService;

    @Autowired
    private IAlertService alertService;

    @Autowired
    private IWorkObjService workObjService;

    @Autowired
    private IAssignService assignService;

    @PostMapping("saveOrUpdateWithRoles")
    public ResponseEntity<Void> saveWorkflow(@RequestBody WorkflowEx workflow) {
        workflowExService.saveOrUpdate(workflow);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }




//    @PostMapping("getPossibleEvents")
//    public ResponseEntity<List<FlowEvent>> getPossibleEvents(String workObjId) {
//
//        List<FlowEvent> events=workObjService.getPossibleEvents(workObjs.get(0),events.get(0));
//        return new ResponseEntity<List<FlowEvent>>(events,HttpStatus.OK);
//    }

    @GetMapping("getDefaultWorkflow")
    public ResponseEntity<WorkflowEx> getDefaultWorkflow() throws Exception {

        WorkflowEx workflowEx=workflowExService.getWorkflowByDefault();
        return new ResponseEntity<WorkflowEx>(workflowEx,HttpStatus.OK);
    }



    @GetMapping("getAvailableAlerts")
    public ResponseEntity<List<WorkObj>> getAvailableAlerts() throws Exception {
        WorkflowEx workflowEx=workflowExService.getWorkflowByDefault();
        List<WorkObj> workObjs= workObjService.getWorkObjsByPointId(workflowEx.getStartPoint().getFlowPointId());
        return new ResponseEntity<List<WorkObj>>(workObjs,HttpStatus.OK);
    }

    @PostMapping("assignToMe")
    public ResponseEntity<Void> assignToMe(@RequestBody List<String> workObjIds,HttpSession session) throws Exception {
        WorkflowEx workflowEx=workflowExService.getWorkflowByDefault();
        String eventId= workflowEx.getStartPoint().getPossibleEvents().get(0).getFlowEventId();
        for(String id : workObjIds){
            WorkObj workObj=workObjService.getWorkObjsByWorkObjId(id);
            FlowEvent event=workObjService.getFlowEventByEventId(eventId);
            List<FlowEvent> events=workObjService.doEvent(workObj,event);
        }

        //assign to me
        Map<String, Object> userInfo =( Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users)userInfo.get("User");
        assignService.AssignTo(user, workObjIds,user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("getMyAlerts")
    public ResponseEntity<List<WorkObj>> getMyAlerts(HttpSession session) throws Exception {

        Map<String, Object> userInfo =( Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users)userInfo.get("User");
        List<Assign> assigns= assignService.getAssignByUser(user);

        List<WorkObj> workObjs=workObjService.getWorkObjsByAssigns(assigns);

        return new ResponseEntity<List<WorkObj>>(workObjs,HttpStatus.OK);
    }


    /***
     * "f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19"
     * @param flowPointId
     * @return
     * @throws Exception
     */
    @GetMapping("getWorkObjsByPointId")
    public ResponseEntity<List<WorkObj>> getWorkObjsByPointId(@RequestParam String flowPointId) throws Exception {

        List<WorkObj> workObjs= workObjService.getWorkObjsByPointId(flowPointId);

        return new ResponseEntity<List<WorkObj>>(workObjs,HttpStatus.OK);
    }


    @GetMapping("test")
    public ResponseEntity<Void> test() throws Exception {

//        Alerts alert=alertService.getSingleAlert("20170728163112212373351046537238");
//
//        WorkflowEx workflowEx=  workflowExService.getWorkflowByFlowId("954dc267-c3e2-43d1-abdb-a83ca2881875");
//
//        List<FlowEvent> events= workObjService.attach(alert,workflowEx);

        List<WorkObj> workObjs= workObjService.getWorkObjsByPointId("f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19");

      //  workObjService.doEvent(workObjs.get(0),events.get(0));


        return new ResponseEntity<Void>(HttpStatus.OK);
    }




    @GetMapping("delete")
    public ResponseEntity<Void> delete() throws Exception {

        workObjService.truncateTable();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("setDefaultWorkflowId")
    public ResponseEntity<Void> setDefaultWorkflowId(@RequestBody Workflow workflow){
        workflowExService.setDefaultWorkflowId(String.valueOf(workflow.getId()), userName);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    @GetMapping("getDefaultWorkflowId")
    public ResponseEntity<Map<String,String>> getDefaultWorkflowId(){
        Map<String,String> result=new HashMap<>();
        String id= workflowExService.getDefaultWorkflowId();
        result.put("defaultWorkflow",id);
        return new ResponseEntity<Map<String,String>>(result, HttpStatus.OK);
    }

}
