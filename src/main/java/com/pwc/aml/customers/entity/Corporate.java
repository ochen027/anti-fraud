package com.pwc.aml.customers.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by whuang072 on 8/8/2017.
 */
@Entity
@Table(name = "Corporate")
public class Corporate extends BaseEntity{


    private static final long serialVersionUID = 1L;


    @Column(name = "cust_id")
    private String customerId;
    @Column(name="cust_ct_no_corp")
    private String customerCertificateNumberCorporate;

    @Column(name="cust_LOB")
    private String custLineOfBusiness;
    @Column(name="cust_phone")
    private String customerPhone;
    @Column(name = "cust_city")
    private String customerCity;

    @Column(name = "cust_country")
    private String customerCountry;
    @Column(name="sus_Flg_corp")
    private boolean IsAMLSuspectCorporate;
    @Transient
    private Representative representative;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCertificateNumberCorporate() {
        return customerCertificateNumberCorporate;
    }

    public void setCustomerCertificateNumberCorporate(String customerCertificateNumberCorporate) {
        this.customerCertificateNumberCorporate = customerCertificateNumberCorporate;
    }

    public String getCustLineOfBusiness() {
        return custLineOfBusiness;
    }

    public void setCustLineOfBusiness(String custLineOfBusiness) {
        this.custLineOfBusiness = custLineOfBusiness;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public boolean isAMLSuspectCorporate() {
        return IsAMLSuspectCorporate;
    }

    public void setAMLSuspectCorporate(boolean AMLSuspectCorporate) {
        IsAMLSuspectCorporate = AMLSuspectCorporate;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }
}
