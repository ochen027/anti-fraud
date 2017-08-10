package com.pwc.component.authorize.roles.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.pwc.component.authorize.roles.entity.RoleMenu;
import com.pwc.component.authorize.roles.service.IRoleMenuService;
import com.pwc.aml.menus.entity.Menus;
import com.pwc.aml.menus.service.IMenusService;
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
import com.pwc.component.authorize.roles.entity.Roles;
import com.pwc.component.authorize.roles.service.IRolesService;

@Controller
@RequestMapping("roles")
public class RolesController {
	
	@Autowired
	private IRolesService rolesService;

	@Autowired
	private IRoleMenuService roleMenuService;

	@GetMapping("listAll")
	public ResponseEntity<List<Roles>> ListAllRoles(){
		return new ResponseEntity<List<Roles>>(rolesService.listAllRoles(), HttpStatus.OK);
	}

	@GetMapping("list/{id}")
	public ResponseEntity<Roles> List(@PathVariable int id){
		return new ResponseEntity<Roles>(rolesService.getSingleRoles(id), HttpStatus.OK);
	}
	@PostMapping("deleteRole/{id}")
	public ResponseEntity<Void> DeleteRole(@PathVariable int id){
		Roles role = rolesService.getSingleRoles(id);
		rolesService.deleteRoles(role);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("createRole")
	public ResponseEntity<Void> CreateRole(@RequestBody Roles r){
		r.setCreationDate(new Date());
		rolesService.createRoles(r);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("updateRole")
	public ResponseEntity<Void> UpdateRole(@RequestBody Roles r ){
		rolesService.updateRoles(r);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("createMenuIntoRole")
	public ResponseEntity<Void> createMenuIntoRole(@RequestBody RoleMenu rm,String roleName){
		rolesService.addRoleIntoMenu(rm,roleName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("updateRoleMenu")
	public ResponseEntity<Void> updateRoleMenu(@RequestBody RoleMenu rm,String roleName){
		rolesService.updateRoleMenu(rm, roleName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("deleteMenuFromRole/{id}")
	public ResponseEntity<Void> deleteMenuFromRole(@PathVariable int id,String roleName){
		rolesService.deleteMenuFromRole(id, roleName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}



	@GetMapping("getRoleMenuByRole")
	public ResponseEntity<List<RoleMenu>> getRoleMenuByRole(@PathVariable int roleId){
		return new ResponseEntity<List<RoleMenu>>(roleMenuService.getRoleMenuByRole(roleId), HttpStatus.OK);
	}
}
