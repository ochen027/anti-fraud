package com.pwc.aml.workflow.entity;

import com.pwc.common.base.entity.BaseEntity;
import com.pwc.component.workflow.entity.FlowPoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class FlowPointEx extends FlowPoint {

    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
