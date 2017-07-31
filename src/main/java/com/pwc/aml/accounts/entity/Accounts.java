package com.pwc.aml.accounts.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="ACCOUNTS")
public class Accounts extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "acct_id")
    private String accountId;

    @Column(name = "cust_id")
    private String customerId;

    @Column(name = "acct_open_dt")
    private Date accountOpenDate;

    @Column(name = "acct_status")
    private String accountStatus;

    @Column(name = "acct_close_dt")
    private Date accountCloseDate;

    @Column(name = "acct_amt")
    private double AccountAmount;

    @Column(name = "acct_open_br")
    private String accountOpenBr;

    @Column(name = "last_upd_br")
    private String lastUpdateBr;

    @Column(name="acct_type")
    private String accountType;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(Date accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getAccountCloseDate() {
        return accountCloseDate;
    }

    public void setAccountCloseDate(Date accountCloseDate) {
        this.accountCloseDate = accountCloseDate;
    }

    public double getAccountAmount() {
        return AccountAmount;
    }

    public void setAccountAmount(double accountAmount) {
        AccountAmount = accountAmount;
    }

    public String getAccountOpenBr() {
        return accountOpenBr;
    }

    public void setAccountOpenBr(String accountOpenBr) {
        this.accountOpenBr = accountOpenBr;
    }

    public String getLastUpdateBr() {
        return lastUpdateBr;
    }

    public void setLastUpdateBr(String accountOpenBr) {
        this.lastUpdateBr = lastUpdateBr;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
