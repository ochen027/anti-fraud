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
@Table(name = "Representative")
public class Representative extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "cust_id")
    private String customerId;
    @Column(name="representative_name")
    private String representativeName;
    @Column(name="representative_age")
    private int representativeAge;
    @Column(name="representative_birthDate")
    private Date representativeBirthDate;
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

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public int getRepresentativeAge() {
        return representativeAge;
    }

    public void setRepresentativeAge(int representativeAge) {
        this.representativeAge = representativeAge;
    }

    public Date getRepresentativeBirthDate() {
        return representativeBirthDate;
    }

    public void setRepresentativeBirthDate(Date representativeBirthDate) {
        this.representativeBirthDate = representativeBirthDate;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
