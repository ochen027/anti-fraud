package com.pwc.aml.dashboard.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.component.assign.dao.IAssignDao;
import com.pwc.component.assign.entity.Assign;
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

	@Autowired
	IAssignDao assignDAO;

	@Autowired
	ICustomerBaseDao customerBaseDAO;
	
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

	@Override
	public List<DashboardResult> getAssignStatus() throws Exception {
		List<FlowPoint> definedPointList = workflowExService.getWorkflowByDefault().getFlowPoints();
		List<DashboardResult> result = new ArrayList<DashboardResult>(definedPointList.size());
		for(FlowPoint fp : definedPointList){
			List<WorkObj> workObjsList = workObjDAO.searchAlertWorkObject(fp.getFlowPointId(), null);
			DashboardResult dr = new DashboardResult();
			List<String> label = new ArrayList<String>(2);
			label.add("Assigned");
			label.add("NotAssigned");
			List<Integer> data = new ArrayList<Integer>(2);
			Integer assignCount = this.getAssignCount(workObjsList);
			data.add(assignCount);
			data.add(workObjsList.size() - assignCount);
			dr.setLabel(label);
			dr.setData(data);
			dr.setPointName(fp.getName());
			result.add(dr);
		}
		return result;
	}

	@Override
	public List<DashboardResult> getSARStatus() throws Exception {
		LocalDate nowDate = LocalDate.now();
		List<DashboardResult> drList = new ArrayList<DashboardResult>(12);
		for(int i =0; i<12; i++){
			LocalDate date = nowDate.minusMonths((long)i);
			Date firstDay = FormatUtils.LocalDateToDate(date.with(TemporalAdjusters.firstDayOfMonth()));
			Date lastDay = FormatUtils.LocalDateToDate(date.with(TemporalAdjusters.lastDayOfMonth()));
			AlertSearchEntity ase = new AlertSearchEntity();
			ase.setCreatedFromDate(firstDay);
			ase.setCreatedToDate(lastDay);
			List<WorkObj> alertsList = workObjDAO.searchAlertWorkObject(null, ase);

			ase.setCustomerIdList(customerBaseDAO.findHighRiskCustomer());
			ase.setAllCustomer(false);
			List<WorkObj> highRiskAlertsList = workObjDAO.searchAlertWorkObject(null, ase);

			DashboardResult dr = new DashboardResult();
			List<String> label = new ArrayList<String>(3);
			List<Integer> data = new ArrayList<Integer>(3);

			label.add("totalCount");
			label.add("sarCount");
			label.add("highRiskCount");
			data.add(alertsList.size());
			data.add(this.getSARCount(alertsList));
			data.add(highRiskAlertsList.size());

			dr.setLabel(label);
			dr.setData(data);
			dr.setPointName(date.getYear()+"-"+date.getMonthValue());
			drList.add(dr);
		}
		return drList;
	}

	@Override
	public List<DashboardResult> getDueStatus() throws Exception {
		LocalDate nowDate = LocalDate.now();
		List<DashboardResult> drList = new ArrayList<DashboardResult>(3);
		List<FlowPoint> definedPointList = workflowExService.getWorkflowByDefault().getFlowPoints();

		Integer overdueDays = 14;
		Integer cutdownDays = overdueDays*2/3;

		Date firstFromDate = FormatUtils.LocalDateToDate(nowDate.minusDays(1L));
		Date firstToDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(cutdownDays-1)));

		HashMap<String, Integer> map1 = this.getDueAlert(firstFromDate, firstToDate, definedPointList);
		DashboardResult dr1 = new DashboardResult();
		List<String> label1 = new ArrayList<String>(map1.size());
		List<Integer> data1 = new ArrayList<Integer>(map1.size());
		for(Map.Entry<String, Integer> entityMap : map1.entrySet()){
			label1.add(entityMap.getKey());
			data1.add(entityMap.getValue());
		}
		dr1.setLabel(label1);
		dr1.setData(data1);
		dr1.setPointName("1-"+cutdownDays);
		drList.add(dr1);


		Date secondFromDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(cutdownDays)));
		Date secondToDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(overdueDays-1)));
		HashMap<String, Integer> map2 = this.getDueAlert(secondFromDate, secondToDate, definedPointList);
		DashboardResult dr2 = new DashboardResult();
		List<String> label2 = new ArrayList<String>(map2.size());
		List<Integer> data2 = new ArrayList<Integer>(map2.size());
		for(Map.Entry<String, Integer> entityMap : map2.entrySet()){
			label2.add(entityMap.getKey());
			data2.add(entityMap.getValue());
		}
		dr2.setLabel(label2);
		dr2.setData(data2);
		dr2.setPointName(cutdownDays+"-"+overdueDays);
		drList.add(dr2);

		Date thirdFromDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(overdueDays)));
		HashMap<String, Integer> map3 = this.getDueAlert(thirdFromDate, null, definedPointList);
		DashboardResult dr3 = new DashboardResult();
		List<String> label3 = new ArrayList<String>(map3.size());
		List<Integer> data3 = new ArrayList<Integer>(map3.size());
		for(Map.Entry<String, Integer> entityMap : map3.entrySet()){
			label3.add(entityMap.getKey());
			data3.add(entityMap.getValue());
		}
		dr3.setLabel(label3);
		dr3.setData(data3);
		dr3.setPointName("overdue");
		drList.add(dr3);

		return drList;
	}


	private Integer getAssignCount(List<WorkObj> woList) throws Exception{
		Integer count = 0;
		for(WorkObj wo : woList){
			if(null != assignDAO.findByObjId(wo.getWorkObjId())){
				count++;
			}
		}
		return count;
	}

	private Integer getSARCount(List<WorkObj> woList) throws Exception{
		Integer count = 0;
		for(WorkObj wo : woList){
			if(wo.isSAR()){
				count++;
			}
		}
		return count;
	}

	private HashMap<String, Integer> getDueAlert(Date fromDate, Date EndDate, List<FlowPoint> fpList) throws Exception{
		HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
		for(FlowPoint fp : fpList){
			String pointName = fp.getName();
			AlertSearchEntity ase = new AlertSearchEntity();
			ase.setCreatedFromDate(fromDate);
			ase.setCreatedToDate(EndDate);
			List<WorkObj> woList = workObjDAO.searchAlertWorkObject(fp.getFlowPointId(),ase);
			Integer count = woList.size();
			resultMap.put(pointName, count);
		}
		return resultMap;
	}


}
