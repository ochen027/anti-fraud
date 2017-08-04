package com.pwc.component.assign.entity;


import com.pwc.common.base.entity.BaseEntity;

import java.util.List;

public class Assign extends BaseEntity {

    private String assignId;
    private String uObjId;
    private String objId;
    private String byId;
    private List<AssignHistory> history;


    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getuObjId() {
        return uObjId;
    }

    public void setuObjId(String uObjId) {
        this.uObjId = uObjId;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getById() {
        return byId;
    }

    public void setById(String byId) {
        this.byId = byId;
    }

    public List<AssignHistory> getHistory() {
        return history;
    }

    public void setHistory(List<AssignHistory> history) {
        this.history = history;
    }
}
