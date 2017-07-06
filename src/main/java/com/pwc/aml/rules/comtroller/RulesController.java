package com.pwc.aml.rules.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.service.IRulesService;

@Controller
@RequestMapping("rules")
public class RulesController {

	@Autowired
	private IRulesService rulesService;
	
	@GetMapping("listAllRules")
	public ResponseEntity<List<RuleScenario>> ListAllRules(){
		List<RuleScenario> list = rulesService.listAllRuleScenario();
		return new ResponseEntity<List<RuleScenario>>(list, HttpStatus.OK);
	}
	
	@GetMapping("findSingleRule/{id}")
	public ResponseEntity<RuleScenario> FindSingleRule(@PathVariable Integer id){
		return new ResponseEntity<RuleScenario>(rulesService.findSingleRuleScenario(id), HttpStatus.OK);
	}
	
	@PostMapping("createRule")
	public ResponseEntity<Void> CreateRule(@RequestBody RuleScenario rScenario){
		rulesService.createRuleScenario(rScenario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("deleteRule/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable("id") int id){
		rulesService.deleteRuleScenario(id);
    	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping("updateRule")
	public ResponseEntity<Void> UpdateRule(@RequestBody RuleScenario rScenario){
		rulesService.updateRuleScenario(rScenario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
