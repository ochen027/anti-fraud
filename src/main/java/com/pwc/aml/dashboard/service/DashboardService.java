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
import com.pwc.aml.dashboard.entity.*;
import com.pwc.component.assign.dao.IAssignDao;
import com.pwc.component.assign.entity.Assign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.alert.entity.AlertSearchEntity;
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
	public DashboardEntity getAssignStatus() throws Exception {
		List<FlowPoint> definedPointList = workflowExService.getWorkflowByDefault().getFlowPoints();
		DashboardEntity dashboardEntity = new DashboardEntity();
		List<String> labels = new ArrayList<String>(4);
		List<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>(2);
		List<String> series = new ArrayList<String>(2);
		series.add("Assigned");
		series.add("Not Assigned");
		ArrayList<Integer> assignData = new ArrayList<Integer>(4);
		ArrayList<Integer> notAssignData = new ArrayList<Integer>(4);

		AlertAssigning aa = new AlertAssigning();

		for(int i=0; i<definedPointList.size();i++){
			List<WorkObj> workObjsList = workObjDAO.searchAlertWorkObject(definedPointList.get(i).getFlowPointId(), null);

			String pointName = definedPointList.get(i).getName();
			if(StringUtils.contains(pointName,"L1 available pool")){
				aa.setUnAssignCountL1(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"L1 review")){
				aa.setAssignCountL1(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"l2 review")){
				aa.setAssignCountL2(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"L2 available pool")){
				aa.setUnAssignCountL2(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"QC review")){
				aa.setAssignCountQC(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"MLRO review")){
				aa.setAssignCountML(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"Qc available pool")){
				aa.setUnAssignCountQC(workObjsList.size());
				continue;
			}
			if(StringUtils.contains(pointName,"MLRO available Pool")){
				aa.setUnAssignCountML(workObjsList.size());
				continue;
			}
		}

		labels.add(0, "L1");
		labels.add(1, "L2");
		labels.add(2, "QC");
		labels.add(3, "MLRO");

		assignData.add(0, aa.getAssignCountL1());
		assignData.add(1, aa.getAssignCountL2());
		assignData.add(2, aa.getAssignCountQC());
		assignData.add(3, aa.getAssignCountML());

		notAssignData.add(0, aa.getUnAssignCountL1());
		notAssignData.add(1, aa.getUnAssignCountL2());
		notAssignData.add(2, aa.getUnAssignCountQC());
		notAssignData.add(3, aa.getUnAssignCountML());

		data.add(0,assignData);
		data.add(1,notAssignData);
		dashboardEntity.setData(data);
		dashboardEntity.setLabels(labels);
		dashboardEntity.setSeries(series);
		return dashboardEntity;
	}

	@Override
	public DashboardEntity getSARStatus() throws Exception {
		LocalDate nowDate = LocalDate.now();
		DashboardEntity de = new DashboardEntity();
		List<String> labels = new ArrayList<String>(12);
		List<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>(3);
		ArrayList<Integer> sarData = new ArrayList<Integer>(12);
		ArrayList<Integer> restData = new ArrayList<Integer>(12);
		ArrayList<Integer> highRiskData = new ArrayList<Integer>(12);
		for(int i =0; i<12; i++){
			LocalDate date = nowDate.minusMonths((long)i);
			Date firstDay = FormatUtils.LocalDateToDate(date.with(TemporalAdjusters.firstDayOfMonth()));
			Date lastDay = FormatUtils.LocalDateToDate(date.with(TemporalAdjusters.lastDayOfMonth()));
			AlertSearchEntity ase = new AlertSearchEntity();
			ase.setCreatedFromDate(firstDay);
			ase.setCreatedToDate(lastDay);
			List<WorkObj> alertsList = workObjDAO.searchAlertWorkObject(null, ase);
			//Inert total Alerts
			labels.add(i, date.getYear()+"-"+date.getMonthValue());

			ase.setCustomerIdList(customerBaseDAO.findHighRiskCustomer());
			ase.setAllCustomer(false);
			List<WorkObj> highRiskAlertsList = workObjDAO.searchAlertWorkObject(null, ase);

			Integer sarCount = this.getSARCount(alertsList);
			sarData.add(i, sarCount);
			highRiskData.add(i, highRiskAlertsList.size());
			restData.add(i, alertsList.size() - sarCount);
		}
		data.add(0, sarData);
		data.add(1, restData);
		data.add(2, highRiskData);
		de.setLabels(labels);
		de.setData(data);
		return de;
	}

	@Override
	public DashboardEntity getDueStatus() throws Exception {
		LocalDate nowDate = LocalDate.now();
		List<FlowPoint> definedPointList = workflowExService.getWorkflowByDefault().getFlowPoints();
		DashboardEntity de = new DashboardEntity();

		Integer overdueDays = 14;
		Integer cutdownDays = overdueDays*2/3;

		Date firstEndDate = FormatUtils.LocalDateToDate(nowDate.minusDays(1L));
		Date firstFromDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(cutdownDays-1)));
		AlertDue alertDue1= this.getDueAlert(firstFromDate, firstEndDate, definedPointList);

		Date secondEndDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(cutdownDays)));
		Date secondFromDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(overdueDays-1)));
		AlertDue alertDue2 = this.getDueAlert(secondFromDate, secondEndDate, definedPointList);

		Date thirdEndDate = FormatUtils.LocalDateToDate(nowDate.minusDays((long)(overdueDays)));
		AlertDue alertDue3 = this.getDueAlert(null, thirdEndDate, definedPointList);

		ArrayList<Integer> data1List = new ArrayList<Integer>(3);
		ArrayList<Integer> data2List = new ArrayList<Integer>(3);
		ArrayList<Integer> data3List = new ArrayList<Integer>(3);
		ArrayList<Integer> data4List = new ArrayList<Integer>(3);

		data1List.add(0, alertDue1.getCountL1());
		data1List.add(1, alertDue2.getCountL1());
		data1List.add(2, alertDue3.getCountL1());

		data2List.add(0, alertDue1.getCountL2());
		data2List.add(1, alertDue2.getCountL2());
		data2List.add(2, alertDue3.getCountL2());

		data3List.add(0, alertDue1.getCountQC());
		data3List.add(1, alertDue2.getCountQC());
		data3List.add(2, alertDue3.getCountQC());

		data4List.add(0, alertDue1.getCountML());
		data4List.add(1, alertDue2.getCountML());
		data4List.add(2, alertDue3.getCountML());


		List<String> labels = new ArrayList<String>(3);
		labels.add(0,"1-"+(cutdownDays+1));
		labels.add(1, cutdownDays+1+"-"+overdueDays);
		labels.add(2, "overdue");


		List<String> series = new ArrayList<String>(4);
		series.add(0, "L1");
		series.add(1, "L2");
		series.add(2, "QC");
		series.add(3, "ML");

		List<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>(4);
		data.add(0, data1List);
		data.add(1, data2List);
		data.add(2, data3List);
		data.add(3, data4List);

		de.setLabels(labels);
		de.setSeries(series);
		de.setData(data);
		return de;
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

	private AlertDue getDueAlert(Date fromDate, Date EndDate, List<FlowPoint> fpList) throws Exception{
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
		Integer countL1 = 0;
		Integer countL2 = 0;
		Integer countQC = 0;
		Integer countML = 0;
		for(Map.Entry<String, Integer> entry : resultMap.entrySet()){
			if(StringUtils.contains(entry.getKey(), "L1 available pool")){
				countL1 += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "L1 review")){
				countL1 += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "L2 available pool")){
				countL2 += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "l2 review")){
				countL2 += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "Qc available pool")){
				countQC += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "QC review")){
				countQC += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "MLRO available Pool")){
				countML += entry.getValue();
				continue;
			}
			if(StringUtils.contains(entry.getKey(), "MLRO review")){
				countML += entry.getValue();
				continue;
			}
		}

		AlertDue ad = new AlertDue();
		ad.setCountL1(countL1);
		ad.setCountL2(countL2);
		ad.setCountQC(countQC);
		ad.setCountML(countML);

		return ad;
	}




}
