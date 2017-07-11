package com.pwc.aml.transation.entity;

import java.util.Date;

public class Transactions {
	
	private String transId;
	private String acctId;
	//private Date asOfDate;
	private String counterPartyId;
	private String currencyCd;
	//private Double transBaseAmt;
	private String transBr;
	private String transBy;
	private String transCdtCd;
	//private Integer transChanel;
	private String transDesc;
	//private Date transDt;
	//private Integer transSeq;
	
	private String asOfDate;
	private String transBaseAmt;
	private String transChanel;
	private String transDt;
	private String transSeq;
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
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
	public String getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}
	public String getTransBaseAmt() {
		return transBaseAmt;
	}
	public void setTransBaseAmt(String transBaseAmt) {
		this.transBaseAmt = transBaseAmt;
	}
	public String getTransChanel() {
		return transChanel;
	}
	public void setTransChanel(String transChanel) {
		this.transChanel = transChanel;
	}
	public String getTransDt() {
		return transDt;
	}
	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}
	public String getTransSeq() {
		return transSeq;
	}
	public void setTransSeq(String transSeq) {
		this.transSeq = transSeq;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	
	
}
