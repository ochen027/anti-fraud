package com.pwc.aml.customers.entity;


import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERS_bak")
public class Customers extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cust_id")
    private String customerId;

    @Column(name = "cust_ct_no_indiv")
    private String customerCertificateNumber;

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

    @Column(name = "cust_age")
    private int customerAge;

    @Column(name = "cust_birth_dt")
    private Date customerBirthDate;

    @Column(name = "cust_city")
    private String customerCity;

    @Column(name = "cust_country")
    private String customerCountry;


    @Column(name = "cust_occupation")
    private String customerOccupation;

    @Column(name = "cust_phone_1")
    private String customerPhone1;

    @Column(name = "PEP_Flg")
    private boolean IsPEP;

    @Column(name = "resi_Flg")
    private boolean IsNonResident;

    @Column(name = "sus_Flg_indiv")
    private boolean IsAMLSuspect;

    @Column(name="cust_type")
    private String customerType;


    @Column(name="representative")
    private String representative;

    @Column(name="representative_id")
    private String representativeId;


    @Column(name="representative_id_type")
    private String representativeIdType;


    @Column(name="cust_ct_no_corp")
    private String customerCertificateNumberCorporate;


    @Column(name="cust_LOB")
    private String custlineOfBusiness;

    @Column(name="sus_Flg_corp")
    private boolean IsAMLSuspectCorporate;



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

    public String getAlertCreationDate() {
        return alertCreationDate;
    }

    public void setAlertCreationDate(String alertCreationDate) {
        this.alertCreationDate = alertCreationDate;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getTransIdArray() {
        return transIdArray;
    }

    public void setTransIdArray(String transIdArray) {
        this.transIdArray = transIdArray;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCertificateNumber() {
        return customerCertificateNumber;
    }

    public void setCustomerCertificateNumber(String customerCertificateNumber) {
        this.customerCertificateNumber = customerCertificateNumber;
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

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public Date getCustomerBirthDate() {
        return customerBirthDate;
    }

    public void setCustomerBirthDate(Date customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerOccupation() {
        return customerOccupation;
    }

    public void setCustomerOccupation(String customerOccupation) {
        this.customerOccupation = customerOccupation;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public boolean isPEP() {
        return IsPEP;
    }

    public void setPEP(boolean PEP) {
        IsPEP = PEP;
    }

    public boolean isNonResident() {
        return IsNonResident;
    }

    public void setNonResident(boolean nonResident) {
        IsNonResident = nonResident;
    }

    public boolean isAMLSuspect() {
        return IsAMLSuspect;
    }

    public void setAMLSuspect(boolean AMLSuspect) {
        IsAMLSuspect = AMLSuspect;
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

    public String getAccountIdArray() {
        return accountIdArray;
    }

    public void setAccountIdArray(String accountIdArray) {
        this.accountIdArray = accountIdArray;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }


    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId;
    }

    public String getRepresentativeIdType() {
        return representativeIdType;
    }

    public void setRepresentativeIdType(String representativeIdType) {
        this.representativeIdType = representativeIdType;
    }


    public String getCustomerCertificateNumberCorporate() {
        return customerCertificateNumberCorporate;
    }

    public void setCustomerCertificateNumberCorporate(String customerCertificateNumberCorporate) {
        this.customerCertificateNumberCorporate = customerCertificateNumberCorporate;
    }

    public String getCustlineOfBusiness() {
        return custlineOfBusiness;
    }

    public void setCustlineOfBusiness(String custlineOfBusiness) {
        this.custlineOfBusiness = custlineOfBusiness;
    }

    public boolean isAMLSuspectCorporate() {
        return IsAMLSuspectCorporate;
    }

    public void setAMLSuspectCorporate(boolean AMLSuspectCorporate) {
        IsAMLSuspectCorporate = AMLSuspectCorporate;
    }
}
