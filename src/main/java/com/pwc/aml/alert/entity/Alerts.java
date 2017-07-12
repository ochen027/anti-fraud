package com.pwc.aml.alert.entity;

import java.util.Date;

public class Alerts {
	
	private String alterId;
	private String alertName;
	private String transId;
	private String alertContents;
	private Date alertCreatedDate;
	private int customerId;
	
	
	
	public String getAlterId() {
		return alterId;
	}
	public void setAlterId(String alterId) {
		this.alterId = alterId;
	}
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getAlertContents() {
		return alertContents;
	}
	public void setAlertContents(String alertContents) {
		this.alertContents = alertContents;
	}
	public Date getAlertCreatedDate() {
		return alertCreatedDate;
	}
	public void setAlertCreatedDate(Date alertCreatedDate) {
		this.alertCreatedDate = alertCreatedDate;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	

}
