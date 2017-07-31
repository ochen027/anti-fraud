package com.pwc.aml.workflow.dao;

import com.pwc.aml.workflow.entity.RolePoint;

import java.util.List;


public interface IRolePointDao {

    void save(RolePoint rolePoint);

    List<RolePoint> findRolePointByFlowId(String flowId);

    RolePoint findRolePointByPointId(String flowPointId);
}
