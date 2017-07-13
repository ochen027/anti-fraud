package com.pwc.aml.workflow.dao;


import com.pwc.aml.workflow.entity.FlowEvent;
import com.pwc.aml.workflow.entity.Workflow;

import java.util.List;

public interface IFlowEventDAO {

    void save(FlowEvent flowEvent);

    void delete(String flowEventId);

    FlowEvent update(FlowEvent flowEvent);

    List<FlowEvent> findByFlowId(String flowId);

    List<FlowEvent> findByPointId(String pointId);

    FlowEvent findByFlowEventId(String flowEventId);
}
