package com.pwc.aml.dashboard.controller;

import java.util.List;

import com.pwc.aml.dashboard.entity.DashboardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pwc.aml.dashboard.entity.DashboardResult;
import com.pwc.aml.dashboard.entity.DashboardSearch;
import com.pwc.aml.dashboard.service.IDashboardService;

@Controller
@RequestMapping("dashboard")
public class DashboardsController {
	
	@Autowired
	IDashboardService dashboardService;
	
	@PostMapping("alerts")
	public ResponseEntity<List<DashboardResult>> getAlertDetail(@RequestBody DashboardSearch dashboardSearch) throws Exception{
		return new ResponseEntity<List<DashboardResult>>(dashboardService.getAlertDetail(dashboardSearch), HttpStatus.OK);
	}


	@GetMapping("assign")
	public ResponseEntity<DashboardEntity> getAssignStatus() throws Exception{
		return new ResponseEntity<DashboardEntity>(dashboardService.getAssignStatus(), HttpStatus.OK);
	}

	@GetMapping("sar")
	public ResponseEntity<DashboardEntity> getSARStatus() throws Exception{
		return new ResponseEntity<DashboardEntity>(dashboardService.getSARStatus(), HttpStatus.OK);
	}

	@GetMapping("due")
	public ResponseEntity<DashboardEntity> getDueStatus() throws Exception{
		return new ResponseEntity<DashboardEntity>(dashboardService.getDueStatus(), HttpStatus.OK);
	}

}
