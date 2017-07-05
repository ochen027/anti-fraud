package com.pwc.aml.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ochen027 on 7/4/2017.
 */
@MappedSuperclass
public class BaseEntity implements Serializable{
    @Column(name="CREATED_BY")
    private String createdBy;
    @Column(name="CREATION_DATE")
    private Date creationDate;
    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Column(name="LAST_UPDATE_DATE")
    private Date lastUpdateDate;
    @Column(name="STATUS")
    private boolean status;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
