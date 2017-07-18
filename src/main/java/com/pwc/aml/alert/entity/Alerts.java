package com.pwc.aml.alert.entity;

public class Alerts {
	
	private String alterId;
	private String alertName;
	private String transId;
	private String alertContents;
	private String alertCreatedDate;
	private int customerId;
	private int accountId;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

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

	public String getAlertCreatedDate() {
		return alertCreatedDate;
	}

	public void setAlertCreatedDate(String alertCreatedDate) {
		this.alertCreatedDate = alertCreatedDate;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	

}
