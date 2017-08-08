package com.pwc.aml.alert.entity;

public class AlertSearchEntity {

    private String customerId;
    private String customerName;
    private String alertId;
    private Double totalAmt;
    private String createdFromDate;
    private String createdToDate;
    private String closedFromDate;
    private String closedToDate;
    private String suspiciousLevel;
    private String colsedBy;


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

    public String getCreatedFromDate() {
        return createdFromDate;
    }

    public void setCreatedFromDate(String createdFromDate) {
        this.createdFromDate = createdFromDate;
    }

    public String getCreatedToDate() {
        return createdToDate;
    }

    public void setCreatedToDate(String createdToDate) {
        this.createdToDate = createdToDate;
    }

    public String getClosedFromDate() {
        return closedFromDate;
    }

    public void setClosedFromDate(String closedFromDate) {
        this.closedFromDate = closedFromDate;
    }

    public String getClosedToDate() {
        return closedToDate;
    }

    public void setClosedToDate(String closedToDate) {
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
}
