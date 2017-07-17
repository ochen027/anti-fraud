package com.pwc.component.workflow.dao;


import com.pwc.component.workflow.entity.FlowPoint;

import java.util.List;

public interface IFlowPointDAO {

    void save(FlowPoint flowPoint);

    void delete(String flowPointId);

    FlowPoint update(FlowPoint flowPoint);

    FlowPoint find(String folwPointId);

    List<FlowPoint> findByFlowId(String flowId);


    void deleteByFlowId(String flowId);
}
