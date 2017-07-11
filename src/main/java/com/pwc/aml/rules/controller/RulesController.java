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

import com.pwc.aml.base.controller.BaseController;
import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.RuleStep;
import com.pwc.aml.rules.service.IRulesService;

@Controller
@RequestMapping("rules")
public class RulesController extends BaseController{

	@Autowired
	private IRulesService rulesService;
	
	@GetMapping("listAll")
	public ResponseEntity<List<RuleScenario>> ListAllRules(){
		List<RuleScenario> list = rulesService.listAllRuleScenario();
		return new ResponseEntity<List<RuleScenario>>(list, HttpStatus.OK);
	}
	
	@GetMapping("findSingle/{id}")
	public ResponseEntity<RuleScenario> FindSingleRule(@PathVariable Integer id){
		return new ResponseEntity<RuleScenario>(rulesService.findSingleRuleScenario(id), HttpStatus.OK);
	}
	
	@PostMapping("createRule")
	public ResponseEntity<Void> CreateRule(@RequestBody RuleScenario rScenario, HttpSession session){
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
	public ResponseEntity<Void> UpdateRule(@RequestBody RuleScenario rScenario){
		rulesService.updateRuleScenario(rScenario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("createStep")
	public ResponseEntity<Void> CreateStep(@RequestBody RuleStep rStep){
		rulesService.createRuleStep(rStep);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("listStepByRule")
	public ResponseEntity<List<RuleStep>> ListStepByRule(@PathVariable("id") int id){
		return new ResponseEntity<List<RuleStep>>(rulesService.listStepByRule(id),HttpStatus.OK);
	}
	

}
