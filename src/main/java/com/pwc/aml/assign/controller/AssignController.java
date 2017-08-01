package com.pwc.aml.assign.controller;

import com.pwc.aml.assign.service.IAssignService;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.entity.WorkflowEx;
import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("assign")
public class AssignController extends BaseController{

@Autowired
private IAssignService assignService;



    @PostMapping("assignToMe")
    public ResponseEntity<Void> assignToMe(@RequestBody List<String> workObjIds, HttpSession session) throws Exception {

        Map<String, Object> userInfo =( Map<String, Object>) session.getAttribute("UserInfo");
        Users user = (Users)userInfo.get("User");
        assignService.AssignTo(user, workObjIds,user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
