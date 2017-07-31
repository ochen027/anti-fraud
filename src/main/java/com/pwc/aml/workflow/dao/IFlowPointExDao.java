package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.component.workflow.entity.FlowPoint;

public interface IFlowPointExDao {
    FlowPointEx getFlowPointExByPointId(String flowPointId);
    FlowPointEx getFlowPointExByPointId(FlowPoint flowPoint);
}
