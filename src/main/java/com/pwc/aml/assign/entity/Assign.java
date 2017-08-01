package com.pwc.aml.assign.entity;


import com.pwc.common.base.entity.BaseEntity;

public class Assign extends BaseEntity {

    private String assignId;
    private String userId;
    private String objectId;

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
