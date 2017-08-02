package com.pwc.component.assign.entity;


import java.util.Date;

public class AssignHistory {

    private String uObjId;
    private Date assignDate;
    private String byId;

    public String getuObjId() {
        return uObjId;
    }

    public void setuObjId(String uObjId) {
        this.uObjId = uObjId;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public String getById() {
        return byId;
    }

    public void setById(String byId) {
        this.byId = byId;
    }
}
