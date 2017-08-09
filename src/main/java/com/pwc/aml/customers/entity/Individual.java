package com.pwc.aml.customers.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by whuang072 on 8/8/2017.
 */
@Entity
@Table(name = "CUSTOMERS")
public class Individual extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "cust_id")
    private String customerId;


    @Column(name = "cust_age")
    private int customerAge;

    @Column(name = "cust_birth_dt")
    private Date customerBirthDate;

    @Column(name = "cust_ct_no_indiv")
    private String customerCertificateNumber;

    @Column(name = "cust_occupation")
    private String customerOccupation;

    @Column(name = "cust_city")
    private String customerCity;

    @Column(name = "cust_country")
    private String customerCountry;



    @Column(name = "cust_phone")
    private String customerPhone;
    @Column(name = "PEP_Flg")
    private boolean IsPEP;

    @Column(name = "resi_Flg")
    private boolean IsNonResident;

    @Column(name = "sus_Flg_indiv")
    private boolean IsAMLSuspect;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getCustomerCertificateNumber() {
        return customerCertificateNumber;
    }

    public void setCustomerCertificateNumber(String customerCertificateNumber) {
        this.customerCertificateNumber = customerCertificateNumber;
    }

    public String getCustomerOccupation() {
        return customerOccupation;
    }

    public void setCustomerOccupation(String customerOccupation) {
        this.customerOccupation = customerOccupation;
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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
