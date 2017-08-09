package com.pwc.aml.customers.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by whuang072 on 8/8/2017.
 */
@Entity
@Table(name = "CUSTOMERS")
public class Representative extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cust_id")
    private String customerId;
    @Column(name="representative_name")
    private String representativeName;
    @Column(name="representative_age")
    private String representativeAge;
    @Column(name="representative_birthdate")
    private String representativeBirthdate;
    @Column(name="representative_id")
    private String representativeId;


    @Column(name="representative_id_type")
    private String representativeIdType;
    @Column(name="representative_occupation")
    private String representativeOccupation;
    @Column(name="representative_city")
    private String representativeCity;
    @Column(name="representative_country")
    private String representativeCountry;
    @Column(name="representative_phone")
    private String representativePhone;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getRepresentativeAge() {
        return representativeAge;
    }

    public void setRepresentativeAge(String representativeAge) {
        this.representativeAge = representativeAge;
    }

    public String getRepresentativeBirthdate() {
        return representativeBirthdate;
    }

    public void setRepresentativeBirthdate(String representativeBirthdate) {
        this.representativeBirthdate = representativeBirthdate;
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

    public String getRepresentativeOccupation() {
        return representativeOccupation;
    }

    public void setRepresentativeOccupation(String representativeOccupation) {
        this.representativeOccupation = representativeOccupation;
    }

    public String getRepresentativeCity() {
        return representativeCity;
    }

    public void setRepresentativeCity(String representativeCity) {
        this.representativeCity = representativeCity;
    }

    public String getRepresentativeCountry() {
        return representativeCountry;
    }

    public void setRepresentativeCountry(String representativeCountry) {
        this.representativeCountry = representativeCountry;
    }

    public String getRepresentativePhone() {
        return representativePhone;
    }

    public void setRepresentativePhone(String representativePhone) {
        this.representativePhone = representativePhone;
    }
}
