package com.pwc.aml.workflow.entity;

import com.pwc.common.base.entity.BaseEntity;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


public class FlowPointEx extends FlowPoint {

    private int roleId;

    private List<FlowEvent> events;


    public List<FlowEvent> getEvents() {
        return events;
    }

    public void setEvents(List<FlowEvent> events) {
        this.events = events;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
