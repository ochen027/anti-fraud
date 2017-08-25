package com.pwc.aml.dashboard.service;

import java.util.List;

import com.pwc.aml.dashboard.entity.DashboardResult;
import com.pwc.aml.dashboard.entity.DashboardSearch;

public interface IDashboardService {
	
	List<DashboardResult> getAlertDetail(DashboardSearch dashboardSearch) throws Exception;

	List<DashboardResult> getAssignStatus() throws Exception;
}
