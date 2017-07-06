package com.pwc.aml.users.controller;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.menus.entity.Menus;
import com.pwc.aml.menus.service.IMenusService;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.entity.Users;
import com.pwc.aml.users.service.IUsersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("user")
public class UsersController {
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
                int userId = u.getUserId();
                u.setUserPwd(null);
                List<Groups> gList = usersService.fetchUserGroup(userId);
                List<Roles> rList = new ArrayList<Roles>();
                for(Groups g : gList){
                    rList = usersService.fetchGroupRole(g.getUserGroupId());
                }
                
                List<Menus> menuList = menusService.listUserMenus(userId);
                Map<String, Object> userInfo = new HashMap<String, Object>(4);
                userInfo.put("User", u);
                userInfo.put("Groups", gList);
                userInfo.put("Roles", rList);
                userInfo.put("Menus", menuList);
                session.setAttribute("UserInfo", userInfo);
                return new ResponseEntity<Map<String, Object>>(userInfo, HttpStatus.OK);

            }
        }
    }
    
    @GetMapping("getSingleUser/{id}")
    public ResponseEntity<Users> GetSingleUser(@PathVariable("id") Integer id){
    	Users u = usersService.findSingleUser(id);
		return new ResponseEntity<Users>(u, HttpStatus.OK);
    }
    
    @GetMapping("listAllUsers")
    public ResponseEntity<List<Users>> ListAllUsers(){
    	List<Users> uList = usersService.listAllUsers();
    	return new ResponseEntity<List<Users>>(uList, HttpStatus.OK);
    }
    
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
    	usersService.deleteUser(id);
    	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("updateUser")
    public ResponseEntity<Users> updateUser(@RequestBody Users u){
    	usersService.updateUser(u);
    	return new ResponseEntity<Users>(u, HttpStatus.OK);
    }
    
    @PostMapping("createUser")
    public ResponseEntity<Void> createUser(@RequestBody Users u){
    	usersService.createUser(u);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    
}
