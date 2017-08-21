package com.pwc.aml.customers.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * table name: @Table(name = "CUSTOMERS")
 */
@Entity
@Table(name = "CUSTOMERS")
public class CustomerBase extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "cust_id")
    private String customerId;
    @Column(name="cust_type")
    private String customerType;

    @Column(name = "cust_first_nm")
    private String customerFirstName;

    @Column(name = "cust_middle_nm")
    private String customerMiddleName;


    @Column(name = "cust_last_nm")
    private String customerLastName;

    @Column(name = "full_nm")
    private String customerFullName;

    @Column(name = "cust_open_br")
    private String customerOpenBranch;//哪家网点开户的
    @Column(name = "last_upd_by")
    private String customerLastUpdatedBy;
    @Column(name = "cust_risk_level")
    private String customerRiskLevel;

    @Transient
    private Individual individual;
    @Transient
    private Corporate corporate;

    @Transient
    private BigDecimal totalTransAmt;
    @Transient
    private int totalTransCount;
    @Transient
    private String transIdArray;
    @Transient
    private String accountIdArray;
    @Transient
    private String businessDate;
    @Transient
    private String alertCreationDate;
    @Transient
    private String alertId;
    @Transient
    private String accountId;



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerMiddleName() {
        return customerMiddleName;
    }

    public void setCustomerMiddleName(String customerMiddleName) {
        this.customerMiddleName = customerMiddleName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerOpenBranch() {
        return customerOpenBranch;
    }

    public void setCustomerOpenBranch(String customerOpenBranch) {
        this.customerOpenBranch = customerOpenBranch;
    }

    public String getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    public void setCustomerLastUpdatedBy(String customerLastUpdatedBy) {
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Corporate getCorporate() {
        return corporate;
    }

    public void setCorporate(Corporate corporate) {
        this.corporate = corporate;
    }

    public BigDecimal getTotalTransAmt() {
        return totalTransAmt;
    }

    public void setTotalTransAmt(BigDecimal totalTransAmt) {
        this.totalTransAmt = totalTransAmt;
    }

    public int getTotalTransCount() {
        return totalTransCount;
    }

    public void setTotalTransCount(int totalTransCount) {
        this.totalTransCount = totalTransCount;
    }

    public String getTransIdArray() {
        return transIdArray;
    }

    public void setTransIdArray(String transIdArray) {
        this.transIdArray = transIdArray;
    }

    public String getAccountIdArray() {
        return accountIdArray;
    }

    public void setAccountIdArray(String accountIdArray) {
        this.accountIdArray = accountIdArray;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getAlertCreationDate() {
        return alertCreationDate;
    }

    public void setAlertCreationDate(String alertCreationDate) {
        this.alertCreationDate = alertCreationDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerRiskLevel() {
        return customerRiskLevel;
    }

    public void setCustomerRiskLevel(String customerRiskLevel) {
        this.customerRiskLevel = customerRiskLevel;
    }
}
