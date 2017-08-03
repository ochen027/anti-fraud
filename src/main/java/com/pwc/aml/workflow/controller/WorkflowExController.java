package com.pwc.aml.workflow.controller;


import com.pwc.aml.alert.service.IAlertService;
import com.pwc.component.assign.entity.Assign;
import com.pwc.component.assign.service.IAssignService;
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
        List<WorkObj> workObjs = workObjService.getWorkObjsByPointId(workflowEx.getStartPoint().getFlowPointId());
        return new ResponseEntity<List<WorkObj>>(workObjs, HttpStatus.OK);
    }

    @PostMapping("assignToMe")
    public ResponseEntity<Void> assignToMe(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {

        for (String id : workObjIds) {
            doEvent("assign", id);
        }
        //assign to me
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
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

        for (String id : workObjIds) {
            doEvent("escalate", id);
        }
        //un assign
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
        assignService.unAssign(user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("close")
    public ResponseEntity<Void> close(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {

        for (String id : workObjIds) {
            doEvent("close", id);
        }
        //un assign
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
        assignService.unAssign(user, workObjIds, user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("return")
    public ResponseEntity<Void> returnToQc(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {

        for (String id : workObjIds) {
            doEvent("return", id);
        }
        //un assign
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users) userInfo.get("User");
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

        List<WorkObj> workObjs = workObjService.getWorkObjsByPointId(flowPointId);

        return new ResponseEntity<List<WorkObj>>(workObjs, HttpStatus.OK);
    }


    private void doEvent(String eventName, String workObjId) throws Exception {
        WorkObj workObj = workObjService.getWorkObjsByWorkObjId(workObjId);
        List<FlowEvent> flowEvents = workObj.getCurrentPoint().getPossibleEvents();
        for (FlowEvent targetEvent : flowEvents) {
            if (eventName.equalsIgnoreCase(targetEvent.getName())) {
                String eventId = workObj.getCurrentPoint().getPossibleEvents().get(0).getFlowEventId();
                FlowEvent event = workObjService.getFlowEventByEventId(eventId);
                List<FlowEvent> events = workObjService.doEvent(workObj, event);
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
