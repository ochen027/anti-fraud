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

    private List<FlowEvent> PossibleEvents;

    public List<FlowEvent> getPossibleEvents() {
        return PossibleEvents;
    }

    public void setPossibleEvents(List<FlowEvent> possibleEvents) {
        PossibleEvents = possibleEvents;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
