package com.pwc.aml.dashboard.service;

import java.util.List;

import com.pwc.aml.dashboard.entity.DashboardEntity;
import com.pwc.aml.dashboard.entity.DashboardResult;
import com.pwc.aml.dashboard.entity.DashboardSearch;

public interface IDashboardService {
	
	List<DashboardResult> getAlertDetail(DashboardSearch dashboardSearch) throws Exception;

	DashboardEntity getAssignStatus() throws Exception;

	DashboardEntity getSARStatus() throws Exception;

	DashboardEntity getDueStatus() throws Exception;
}
