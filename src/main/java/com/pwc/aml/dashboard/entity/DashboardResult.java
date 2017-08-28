package com.pwc.aml.dashboard.entity;

import java.util.ArrayList;
import java.util.List;

public class DashboardResult {
	
	private List<String> label;
	private List<Integer> data;
	private String pointName;
	public List<String> getLabel() {
		return label;
	}
	public void setLabel(List<String> label) {
		this.label = label;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
}
