package com.pwc.aml.users.controller;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.groups.service.IGroupsService;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.entity.UserGroupRoleBean;
import com.pwc.aml.users.entity.Users;
import com.pwc.aml.users.service.IUsersService;
import jdk.net.SocketFlow;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.*;

@Controller
@RequestMapping("user")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @PostMapping("loginUser")
    public ResponseEntity<Map<String, Object>> LoginUser(@RequestBody Users users, HttpSession session) {
        Users u = usersService.checkUserName(users);
        if(null == u){
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }else{
            if(!StringUtils.equals(u.getUserPwd(), users.getUserPwd())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }else{
                int userId = u.getUserId();
                u.setUserPwd(null);
                List<Groups> gList = usersService.fetchUserGroup(userId);
                List<Roles> rList = new ArrayList<Roles>();
                for(Groups g : gList){
                    rList = usersService.fetchGroupRole(g.getUserGroupId());
                }
                Map<String, Object> userInfo = new HashMap<String, Object>(3);
                userInfo.put("User", u);
                userInfo.put("Groups", gList);
                userInfo.put("Roles", rList);
                session.setAttribute("UserInfo", userInfo);
                return new ResponseEntity<Map<String, Object>>(userInfo, HttpStatus.OK);

            }
        }
    }
}
