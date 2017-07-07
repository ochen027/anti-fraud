package com.pwc.aml.groups.controller;

import java.util.List;

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

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.groups.service.IGroupsService;

@Controller
@RequestMapping("group")
public class GroupsController {
	
	@Autowired
	private IGroupsService groupsService;
	
	@GetMapping("listAllGroups")
	public ResponseEntity<List<Groups>> ListAllGroups(){
		return new ResponseEntity<List<Groups>>(groupsService.listAllGroups(), HttpStatus.OK);
	}
	
	@GetMapping("findSingleGroup/{id}")
	public ResponseEntity<Groups> FindSingleGroup(@PathVariable int id){
		return new ResponseEntity<Groups>(groupsService.getSingleGroups(id), HttpStatus.OK);
	}
	
	@GetMapping("deleteGroup/{id}")
	public ResponseEntity<Void> DeleteGroup(@PathVariable int id){
		groupsService.deleteGroups(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("createGroup")
	public ResponseEntity<Void> CreateGroup(@RequestBody Groups g){
		groupsService.createGroups(g);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("updateGroup")
	public ResponseEntity<Void> UpdateGroup(@RequestBody Groups g){
		groupsService.updateGroups(g);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
