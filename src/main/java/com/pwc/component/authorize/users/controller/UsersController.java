package com.pwc.component.authorize.users.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.entity.Users;
import com.pwc.component.authorize.users.service.IUsersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.common.base.controller.BaseController;
import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.aml.menus.entity.Menus;
import com.pwc.aml.menus.service.IMenusService;
import com.pwc.component.authorize.roles.entity.Roles;

@Controller
@RequestMapping("user")
public class UsersController extends BaseController{
    @Autowired
    private IUsersService usersService;
    
    @Autowired
    private IMenusService menusService;

    @PostMapping("loginUser")
    public ResponseEntity<Map<String, Object>> LoginUser(@RequestBody Users users, HttpSession session) {
        Users u = usersService.checkUserName(users);
        if(null == u){
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }else{
            if(!StringUtils.equals(u.getUserPwd(), users.getUserPwd())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }else{
                int userId = u.getId();
                u.setUserPwd(null);
                List<Groups> gList = usersService.fetchUserGroup(userId);
                Set<Roles> rSet = new HashSet<Roles>();
                for(Groups g : gList){
                    List<Roles> rList = usersService.fetchGroupRole(g.getId());
                    rSet.addAll(rList);
                }
                
                List<Menus> menuList = menusService.listUserMenus(userId);
                Map<String, Object> userInfo = new HashMap<String, Object>(4);
                userInfo.put("User", u);
                userInfo.put("Groups", gList);
                userInfo.put("Roles", rSet);
                userInfo.put("Menus", menuList);
                session.setAttribute("UserInfo", userInfo);
                session.setAttribute("userName", u.getUserName());
                userName = u.getUserName();
                return new ResponseEntity<Map<String, Object>>(userInfo, HttpStatus.OK);

            }
        }
    }
    
    @GetMapping("getSingleUser/{id}")
    public ResponseEntity<Users> GetSingleUser(@PathVariable("id") int id){
    	Users u = usersService.findSingleUser(id);
		return new ResponseEntity<Users>(u, HttpStatus.OK);
    }
    
    @GetMapping("listAllUsers")
    public ResponseEntity<List<Users>> ListAllUsers(){
    	List<Users> uList = usersService.listAllUsers();
    	return new ResponseEntity<List<Users>>(uList, HttpStatus.OK);
    }
    
    @GetMapping("deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
    	usersService.deleteUser(id, userName);
    	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("updateUser")
    public ResponseEntity<Users> updateUser(@RequestBody Users u){
    	usersService.updateUser(u, userName);
    	return new ResponseEntity<Users>(u, HttpStatus.OK);
    }
    
    @PostMapping("createUser")
    public ResponseEntity<Void> createUser(@RequestBody Users u){
    	usersService.createUser(u, userName);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("createUserIntoGroup")
    public ResponseEntity<Void> createUserIntoGroup(@RequestBody UserGroup ug){
        usersService.addUserIntoGroup(ug, userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("updateUserGroup")
    public ResponseEntity<Void> updateUserGroup(@RequestBody UserGroup ug){
        usersService.updateUserGroup(ug, userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("deleteUserGroup/{id}")
    public ResponseEntity<Void> deleteUserGroup(@PathVariable int id){
        usersService.deleteUserFromGroup(id, userName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
