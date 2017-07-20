package com.pwc.aml.alert.entity;

import com.pwc.aml.comments.entity.Comments;
import com.pwc.aml.documents.entity.Documents;
import com.pwc.aml.transation.entity.Transactions;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Alerts {
	
	private String alterId;
	private String alertName;
	private String alertContents;
	private String customerId;
	private String accountId;
	private List<Transactions> transList;
	private String scenarioId;
	private List<Documents> docList;
	private List<Comments> commentsList;
	private String businessDate;
	private String totalAmt;
	private String createdDate;
	private String alertDesc;

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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
}
