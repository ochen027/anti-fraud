package com.pwc.aml.rules.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.common.base.controller.BaseController;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.rules.service.IRulesService;

@Controller
@RequestMapping("rules")
public class RulesController extends BaseController{

	@Autowired
	private IRulesService rulesService;
	

	@GetMapping("listAll")
	public ResponseEntity<List<Scenario>> ListAllRules(){
		List<Scenario> list = rulesService.listAllRuleScenario();
		return new ResponseEntity<List<Scenario>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getSingle/{id}")
	public ResponseEntity<Scenario> FindSingleRule(@PathVariable Integer id){
		return new ResponseEntity<Scenario>(rulesService.findSingleRuleScenario(id), HttpStatus.OK);
	}
	
	@PostMapping("createRule")
	public ResponseEntity<Void> CreateRule(@RequestBody Scenario rScenario, HttpSession session){
		rScenario.setCreatedBy(userName);
		rScenario.setLastUpdatedBy(userName);
		rulesService.createRuleScenario(rScenario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("delete/{id}")
    public ResponseEntity<Void> DeleteRule(@PathVariable("id") int id){
		rulesService.deleteRuleScenario(id);
    	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping("update")
	public ResponseEntity<Void> UpdateRule(@RequestBody Scenario rScenario){
		rulesService.updateRuleScenario(rScenario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	@GetMapping("getRuleScript/{id}")
	public ResponseEntity<String> GetRuleScript(@PathVariable("id") int id){
		String s = rulesService.getRuleScript(id);
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
	**/


	@GetMapping("executeRules/{id}")
	public ResponseEntity<Void> ExecuteRules(@PathVariable("id") int id) throws Exception{
		rulesService.executeRuleEngine(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("saveOrUpdate")
	public ResponseEntity<Scenario> saveOrUpdate(@RequestBody Scenario Scenario){
		Scenario t=rulesService.saveOrUpdate(Scenario);
		return new ResponseEntity<Scenario>(t,HttpStatus.OK);
	}

	
}
