package com.pwc.aml.reports.entity;

public class Reports {

    private String date;
    private Integer transCount;
    private Integer alertCount;
    private Integer alertClosedCount;
    private Integer alertSuppressCount;
    private Integer alertSARCount;
    private Integer alertActiveCount;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTransCount() {
        return transCount;
    }

    public void setTransCount(Integer transCount) {
        this.transCount = transCount;
    }

    public Integer getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(Integer alertCount) {
        this.alertCount = alertCount;
    }

    public Integer getAlertClosedCount() {
        return alertClosedCount;
    }

    public void setAlertClosedCount(Integer alertClosedCount) {
        this.alertClosedCount = alertClosedCount;
    }

    public Integer getAlertSuppressCount() {
        return alertSuppressCount;
    }

    public void setAlertSuppressCount(Integer alertSuppressCount) {
        this.alertSuppressCount = alertSuppressCount;
    }

    public Integer getAlertSARCount() {
        return alertSARCount;
    }

    public void setAlertSARCount(Integer alertSARCount) {
        this.alertSARCount = alertSARCount;
    }

    public Integer getAlertActiveCount() {
        return alertActiveCount;
    }

    public void setAlertActiveCount(Integer alertActiveCount) {
        this.alertActiveCount = alertActiveCount;
    }
}
