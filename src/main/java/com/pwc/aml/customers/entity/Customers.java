package com.pwc.aml.customers.entity;


import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERS")
public class Customers extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cust_id")
    private String customerId;

    @Column(name = "cust_ct_no")
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

    @Column(name = "sus_Flg")
    private boolean IsAMLSuspect;


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
}
