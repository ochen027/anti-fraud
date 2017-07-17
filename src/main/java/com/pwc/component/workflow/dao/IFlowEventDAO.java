package com.pwc.component.workflow.dao;


import com.pwc.component.workflow.entity.FlowEvent;

import java.util.List;

public interface IFlowEventDAO {

    void save(FlowEvent flowEvent);

    void delete(String flowEventId);

    FlowEvent update(FlowEvent flowEvent);

    List<FlowEvent> findByFlowId(String flowId);

    List<FlowEvent> findByPointId(String pointId);

    FlowEvent findByFlowEventId(String flowEventId);

    void deleteByFlowId(String flowId);
}
