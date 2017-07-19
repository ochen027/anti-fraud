package com.pwc.aml.workflow.entity;


import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLEPOINT")
public class RolePoint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "ROLEID")
    private int roleId;

    @Column(name = "FLOWPOINTID")
    private String flowPointId;


    public RolePoint() {

    }

    public RolePoint(int roleId, String flowPointId) {
        this.roleId = roleId;
        this.flowPointId = flowPointId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFlowPointId() {
        return flowPointId;
    }

    public void setFlowPointId(String flowPointId) {
        this.flowPointId = flowPointId;
    }
}
