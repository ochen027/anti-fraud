package com.pwc.component.authorize.groups.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.groups.service.IGroupRoleService;
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

import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.component.authorize.groups.service.IGroupsService;

@Controller
@RequestMapping("group")
public class GroupsController{
	
	@Autowired
	private IGroupsService groupsService;

	@Autowired
	private IGroupRoleService groupRoleService;
	
	@GetMapping("listAllGroups")
	public ResponseEntity<List<Groups>> ListAllGroups(){
		return new ResponseEntity<List<Groups>>(groupsService.listAllGroups(), HttpStatus.OK);
	}
	
	@GetMapping("findSingleGroup/{id}")
	public ResponseEntity<Groups> FindSingleGroup(@PathVariable int id){
		return new ResponseEntity<Groups>(groupsService.getSingleGroups(id), HttpStatus.OK);
	}
	
	@PutMapping("deleteGroup/{id}")
	public ResponseEntity<Void> DeleteGroup(@PathVariable int id, HttpSession session){
		groupsService.deleteGroups(id, (String)session.getAttribute("userName"));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("createGroup")
	public ResponseEntity<Void> CreateGroup(@RequestBody Groups g, HttpSession session){
		g.setCreationDate(new Date());
		groupsService.createGroups(g, (String)session.getAttribute("userName"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("updateGroup")
	public ResponseEntity<Void> UpdateGroup(@RequestBody Groups g, HttpSession session){
		groupsService.updateGroups(g, (String)session.getAttribute("userName"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("saveOrUpdateGroup")
	public ResponseEntity<Void> saveOrUpdate(@RequestBody Groups g, HttpSession session){
		//groupsService.createGroups(g, (String)session.getAttribute("userName"));
		groupsService.saveOrUpdate(g, (String)session.getAttribute("userName"));
		List<GroupRole> groupRoles = g.getGroupRolesList();
		groupRoleService.createGroupRole(g,groupRoles,(String)session.getAttribute("userName"));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("listGroupRoles/{groupId}")
	public ResponseEntity<List<GroupRole>> ListAllGroupRoles(@PathVariable int groupId){

		return new ResponseEntity<List<GroupRole>>(groupRoleService.getAllRolesByGroup(groupId), HttpStatus.OK);
	}


}
