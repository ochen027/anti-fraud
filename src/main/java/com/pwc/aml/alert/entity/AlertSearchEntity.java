package com.pwc.aml.alert.entity;

import java.util.Date;
import java.util.List;

public class AlertSearchEntity {

    private String customerId;
    private String customerName;
    private String alertId;
    private Double totalAmt;
    private Date createdFromDate;
    private Date createdToDate;
    private Date closedFromDate;
    private Date closedToDate;
    private String suspiciousLevel;
    private String colsedBy;
    private List<String> customerIdList;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Date getCreatedFromDate() {
        return createdFromDate;
    }

    public void setCreatedFromDate(Date createdFromDate) {
        this.createdFromDate = createdFromDate;
    }

    public Date getCreatedToDate() {
        return createdToDate;
    }

    public void setCreatedToDate(Date createdToDate) {
        this.createdToDate = createdToDate;
    }

    public Date getClosedFromDate() {
        return closedFromDate;
    }

    public void setClosedFromDate(Date closedFromDate) {
        this.closedFromDate = closedFromDate;
    }

    public Date getClosedToDate() {
        return closedToDate;
    }

    public void setClosedToDate(Date closedToDate) {
        this.closedToDate = closedToDate;
    }

    public String getSuspiciousLevel() {
        return suspiciousLevel;
    }

    public void setSuspiciousLevel(String suspiciousLevel) {
        this.suspiciousLevel = suspiciousLevel;
    }

    public String getColsedBy() {
        return colsedBy;
    }

    public void setColsedBy(String colsedBy) {
        this.colsedBy = colsedBy;
    }

    public List<String> getCustomerIdList() {
        return customerIdList;
    }

    public void setCustomerIdList(List<String> customerIdList) {
        this.customerIdList = customerIdList;
    }
}
