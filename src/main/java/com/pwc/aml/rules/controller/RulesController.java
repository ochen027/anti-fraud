package com.pwc.aml.rules.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.pwc.aml.rules.entity.RuleScenario;
import com.pwc.aml.rules.entity.Rules;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<Void> ExecuteRules(@PathVariable("id") int id, HttpSession session) throws Exception{
		Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("UserInfo");
		Users user = (Users) userInfo.get("User");
		rulesService.executeRuleEngine(id,user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("saveOrUpdate")
	public ResponseEntity<Scenario> saveOrUpdate(@RequestBody Scenario Scenario, HttpSession session){
		Map<String, Object> userInfo= (Map<String, Object>)session.getAttribute("UserInfo");
		Users u=(Users) userInfo.get("User");
		Scenario t=rulesService.saveOrUpdate(Scenario,u);
		return new ResponseEntity<Scenario>(t,HttpStatus.OK);
	}

	@PostMapping("saveOrUpdateRules")
	public ResponseEntity<Rules> saveOrUpdateRules(@RequestBody Rules rules, HttpSession session){
		Map<String, Object> userInfo= (Map<String, Object>)session.getAttribute("UserInfo");
		Users u=(Users) userInfo.get("User");
		Rules t=rulesService.saveOrUpdateRules(rules,u);
		return new ResponseEntity<Rules>(t,HttpStatus.OK);
	}


    @GetMapping("listAllRules")
	public ResponseEntity<List<Rules>> listAllRules(){
		List<Rules> list = rulesService.listAllRules();
		return new ResponseEntity<List<Rules>>(list, HttpStatus.OK);
	}

    @PostMapping("listRuleScenarioByRuleId")
    public ResponseEntity<List<RuleScenario>> listRuleScenarioByRuleId(@RequestBody Rules rules){
        List<RuleScenario> list = rulesService.findRuleScenarioByRuleId(rules.getId());
        return new ResponseEntity<List<RuleScenario>>(list, HttpStatus.OK);
    }

	@GetMapping("getDefaultRuleId")
	public ResponseEntity<String> getDefaultRuleId(){
		return new ResponseEntity<String>(rulesService.getDefaultRuleId(), HttpStatus.OK);
	}

	@PostMapping("setDefaultRuleId")
	public ResponseEntity<Void> setDefaultRuleId(@RequestBody Rules rules){
		rulesService.setDefaultRuleId(("" +rules.getId()), userName);
		return new ResponseEntity<Void>( HttpStatus.OK);
	}

    @GetMapping("runRule/{id}")
	public ResponseEntity<Void> RunRule(@PathVariable("id") int ruleId, HttpSession session){
		Map<String, Object> userInfo= (Map<String, Object>)session.getAttribute("UserInfo");
		Users u=(Users) userInfo.get("User");
		rulesService.runRule(ruleId,u);
    	return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
