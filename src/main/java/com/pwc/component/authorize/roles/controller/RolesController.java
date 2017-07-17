package com.pwc.component.authorize.roles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.component.authorize.roles.entity.Roles;
import com.pwc.component.authorize.roles.service.IRolesService;

@Controller
@RequestMapping("roles")
public class RolesController {
	
	@Autowired
	private IRolesService rolesService;
	
	@GetMapping("listAll")
	public ResponseEntity<List<Roles>> ListAllRoles(){
		return new ResponseEntity<List<Roles>>(rolesService.listAllRoles(), HttpStatus.OK);
	}

	
}
