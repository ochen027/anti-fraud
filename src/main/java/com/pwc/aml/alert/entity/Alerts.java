package com.pwc.aml.alert.entity;

import com.pwc.component.comments.entity.Comments;
import com.pwc.aml.documents.entity.Documents;
import com.pwc.aml.transation.entity.Transactions;

import java.util.List;

public class Alerts {
	
	private String alertId;
	private String alertName;
	private String alertContents;
	private String customerId;
	private List<Transactions> transList;
	private String scenarioId;
	private List<Documents> docList;
	private List<Comments> commentsList;
	private String businessDate;
	private String totalAmt;
	private String createdDate;
	private String alertDesc;
	private String customerName;
	private String days;
	private String createdBy;
	private String accountId;

	public String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alterId) {
		this.alertId = alterId;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getAlertContents() {
		return alertContents;
	}

	public void setAlertContents(String alertContents) {
		this.alertContents = alertContents;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<Transactions> getTransList() {
		return transList;
	}

	public void setTransList(List<Transactions> transList) {
		this.transList = transList;
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public List<Documents> getDocList() {
		return docList;
	}

	public void setDocList(List<Documents> docList) {
		this.docList = docList;
	}

	public List<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getAlertDesc() {
		return alertDesc;
	}

	public void setAlertDesc(String alertDesc) {
		this.alertDesc = alertDesc;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
