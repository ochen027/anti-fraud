package com.pwc.aml.dashboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.dashboard.entity.DashboardResult;
import com.pwc.aml.dashboard.entity.DashboardSearch;
import com.pwc.aml.workflow.dao.IWorkObjDao;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.workflow.entity.FlowPoint;

@Service
public class DashboardService implements IDashboardService {
	
	@Autowired
	IWorkflowExService workflowExService;
	
	@Autowired
	IWorkObjDao workObjDAO;
	
	@Override
	public List<DashboardResult> getAlertDetail(DashboardSearch dashboardSearch) throws Exception {
		if(StringUtils.isNotBlank(dashboardSearch.getStartDate()) && StringUtils.isNotBlank(dashboardSearch.getEndDate())){
			AlertSearchEntity ase = new AlertSearchEntity();
			Date startDate = FormatUtils.StringToDate(dashboardSearch.getStartDate());
			Date endDate = FormatUtils.StringToDate(dashboardSearch.getEndDate());
			ase.setClosedFromDate(startDate);
			ase.setClosedToDate(endDate);
			List<FlowPoint> definedPointList = workflowExService.getWorkflowByDefault().getFlowPoints();
			List<WorkObj> workObjTotal = workObjDAO.searchAlertWorkObject(null, ase);
			Integer totalCount = workObjTotal.size();
			List<DashboardResult> dBoardList = new ArrayList<DashboardResult>(definedPointList.size());
			for(FlowPoint fp : definedPointList){
				DashboardResult dBoard = new DashboardResult();
				List<String> label = new ArrayList<String>(2);
				label.add(fp.getName());
				label.add("Rest");
				List<WorkObj> workObjList = workObjDAO.searchAlertWorkObject(fp.getFlowPointId(), ase);
				List<Integer> data = new ArrayList<Integer>(2);
				data.add(workObjList.size());
				data.add(totalCount - workObjList.size());
				dBoard.setLabel(label);
				dBoard.setData(data);
				dBoard.setPointName(fp.getName());
				dBoardList.add(dBoard);
			}
			return dBoardList;
		}
		return null;
	}
	
}
