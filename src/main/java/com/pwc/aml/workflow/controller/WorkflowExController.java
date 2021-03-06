package com.pwc.aml.workflow.controller;


import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.service.IAlertService;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.assign.entity.Assign;
import com.pwc.component.assign.service.IAssignService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;
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
public class WorkflowExController extends BaseController {

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


//

    @GetMapping("getDefaultWorkflow")
    public ResponseEntity<WorkflowEx> getDefaultWorkflow() throws Exception {

        WorkflowEx workflowEx = workflowExService.getWorkflowByDefault();
        return new ResponseEntity<WorkflowEx>(workflowEx, HttpStatus.OK);
    }


    @GetMapping("getAvailableAlerts")
    public ResponseEntity<List<WorkObj>> getAvailableAlerts() throws Exception {
        WorkflowEx workflowEx = workflowExService.getWorkflowByDefault();
        List<WorkObj> workObjs = workObjService.getWorkObjsByPointId(workflowEx.getStartPoint().getFlowPointId(), null);
        return new ResponseEntity<List<WorkObj>>(workObjs, HttpStatus.OK);
    }

    @PostMapping("getWorkObjById")
    public ResponseEntity<WorkObj> getWorkObjById(@RequestBody String id) throws Exception {
        WorkObj workObj=workObjService.getWorkObjsByWorkObjId(id);
        return new ResponseEntity<WorkObj>(workObj, HttpStatus.OK);
    }


    @PostMapping("getClosedAlerts")
    public ResponseEntity<List<WorkObj>> getClosedAlerts(@RequestBody AlertSearchEntity ase) throws Exception {
        WorkflowEx workflowEx = workflowExService.getWorkflowByDefault();
        List<WorkObj> workObjs = workObjService.getWorkObjsByPointId(workflowEx.getEndPoint().getFlowPointId(), ase);
        return new ResponseEntity<List<WorkObj>>(workObjs, HttpStatus.OK);
    }

    @PostMapping("assignToMe")
    public ResponseEntity<Void> assignToMe(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
        for (String id : workObjIds) {
            WorkObj workObj = workObjService.getWorkObjsByWorkObjId(id);
            doEvent("assign", workObj);
        }
        //assign to me

        assignService.assignTo(user, user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



    @GetMapping("getMyAlerts")
    public ResponseEntity<List<WorkObj>> getMyAlerts(HttpSession session) throws Exception {

        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
        List<Assign> assigns = assignService.getAssignByUser(user);

        List<WorkObj> workObjs = workObjService.getWorkObjsByAssigns(assigns);

        return new ResponseEntity<List<WorkObj>>(workObjs, HttpStatus.OK);
    }


    @PostMapping("escalate")
    public ResponseEntity<Void> escalate(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");

        for (String id : workObjIds) {
            WorkObj workObj = workObjService.getWorkObjsByWorkObjId(id);
            doEvent("escalate", workObj);
        }
        //un assign
        assignService.unAssign(user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("close")
    public ResponseEntity<Void> close(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {

        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");

        for (String id : workObjIds) {
            WorkObj workObj = workObjService.getWorkObjsByWorkObjId(id);
            doEvent("close", workObj);
        }
        //un assign
        assignService.unAssign(user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("sar")
    public ResponseEntity<Void> sar(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {

        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");

        for (String id : workObjIds) {
            WorkObj workObj = workObjService.getWorkObjsByWorkObjId(id);
            workObj.setSAR(true);
            workObjService.updateWorkObj(workObj, userName);
            doEvent("sar", workObj);
        }
        //un assign
        assignService.unAssign(user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("returnToQc")
    public ResponseEntity<Void> returnToQc(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
        for (String id : workObjIds) {
            WorkObj workObj = workObjService.getWorkObjsByWorkObjId(id);
            doEvent("return", workObj);
        }
        //un assign

        assignService.unAssign(user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



    /***
     * "f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19"
     * @param flowPointId
     * @return
     * @throws Exception
     */
    @GetMapping("getWorkObjsByPointId")
    public ResponseEntity<List<WorkObj>> getWorkObjsByPointId(@RequestParam String flowPointId) throws Exception {
        List<WorkObj> workObjs = workObjService.getWorkObjsByPointId(flowPointId, null);
        return new ResponseEntity<List<WorkObj>>(workObjs, HttpStatus.OK);
    }


    private void doEvent(String eventName, WorkObj workObj) throws Exception {
        //WorkObj workObj = workObjService.getWorkObjsByWorkObjId(workObjId);
        List<FlowEvent> flowEvents = workObj.getCurrentPoint().getPossibleEvents();
        for (FlowEvent targetEvent : flowEvents) {
            if (eventName.equalsIgnoreCase(targetEvent.getName())) {
                String eventId = targetEvent.getFlowEventId();
                FlowEvent event = workObjService.getFlowEventByEventId(eventId);
                List<FlowEvent> events = workObjService.doEvent(workObj, event, userName);
                break;
            }
        }
    }

    @GetMapping("delete")
    public ResponseEntity<Void> delete() throws Exception {

        alertService.truncateTable();
        workObjService.truncateTable();
        assignService.truncateTable();

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PostMapping("UpdateSuspiciousType")
    public ResponseEntity<Void> UpdateSuspiciousType(@RequestBody WorkObj workObj) throws Exception {
        WorkObj target=workObjService.getWorkObjsByWorkObjId(workObj.getWorkObjId());
        target.setSuspiciousType(workObj.getSuspiciousType());
        workObjService.updateWorkObj(target,userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("setDefaultWorkflowId")
    public ResponseEntity<Void> setDefaultWorkflowId(@RequestBody Workflow workflow) {
        workflowExService.setDefaultWorkflowId(String.valueOf(workflow.getId()), userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("getDefaultWorkflowId")
    public ResponseEntity<Map<String, String>> getDefaultWorkflowId() {
        Map<String, String> result = new HashMap<>();
        String id = workflowExService.getDefaultWorkflowId();
        result.put("defaultWorkflow", id);
        return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
    }

}
