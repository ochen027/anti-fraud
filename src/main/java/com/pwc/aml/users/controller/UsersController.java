package com.pwc.aml.users.controller;

import com.pwc.aml.groups.service.IGroupsService;
import com.pwc.aml.users.entity.UserGroupRoleBean;
import com.pwc.aml.users.entity.Users;
import com.pwc.aml.users.service.IUsersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UsersController {
    @Autowired
    private IUsersService usersService;




    @PostMapping("loginUser")
    public ResponseEntity<Users> LoginUser(@RequestBody Users users) {
        Users u = usersService.checkUserName(users);
        if(null == u){
            return new ResponseEntity<Users>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }else{
            if(!StringUtils.equals(u.getUserPwd(), users.getUserPwd())){
                return new ResponseEntity<Users>(HttpStatus.FORBIDDEN);
            }else{
                return new ResponseEntity<Users>(u, HttpStatus.OK);
            }
        }
    }

    @GetMapping("loginUserGroup/{id}")
    public ResponseEntity<List<UserGroupRoleBean>> LoginUserGroup(@PathVariable("id") int userId) {
        return new ResponseEntity<List<UserGroupRoleBean>>(usersService.fetchUserGroup(userId), HttpStatus.OK);
    }

    @GetMapping("loginGroupRole/{id}")
    public ResponseEntity<List<UserGroupRoleBean>> LoginGroupRole(@PathVariable("id") int groupId) {
        return new ResponseEntity<List<UserGroupRoleBean>>(usersService.fetchGroupRole(groupId), HttpStatus.OK);
    }



}
