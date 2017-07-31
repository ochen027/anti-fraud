package com.pwc.aml.transation.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Transactions {
	
	private String transId;
	private String acctId;
	private String asOfDate;
	private String counterPartyId;
	private String currencyCd;
	private BigDecimal transBaseAmt;
	private String transBr;
	private String transBy;
	private String transCdtCd;
	private String transDesc;
	private String transDt;
	private Integer transSeq;
	private String alertType;
	private String counterBank;
	private String counterBankLocation;
	private String counterName;
	private String transType;


	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public BigDecimal getTransBaseAmt() {
		return transBaseAmt;
	}

	public void setTransBaseAmt(BigDecimal transBaseAmt) {
		this.transBaseAmt = transBaseAmt;
	}

	public String getTransBr() {
		return transBr;
	}

	public void setTransBr(String transBr) {
		this.transBr = transBr;
	}

	public String getTransBy() {
		return transBy;
	}

	public void setTransBy(String transBy) {
		this.transBy = transBy;
	}

	public String getTransCdtCd() {
		return transCdtCd;
	}

	public void setTransCdtCd(String transCdtCd) {
		this.transCdtCd = transCdtCd;
	}

	public String getTransDesc() {
		return transDesc;
	}

	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public Integer getTransSeq() {
		return transSeq;
	}

	public void setTransSeq(Integer transSeq) {
		this.transSeq = transSeq;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getCounterBank() {
		return counterBank;
	}

	public void setCounterBank(String counterBank) {
		this.counterBank = counterBank;
	}

	public String getCounterBankLocation() {
		return counterBankLocation;
	}

	public void setCounterBankLocation(String counterBankLocation) {
		this.counterBankLocation = counterBankLocation;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
}
