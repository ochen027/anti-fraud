package com.pwc.aml.workflow.dao;

import com.pwc.aml.workflow.entity.FlowPointEx;
import com.pwc.aml.workflow.entity.RolePoint;
import com.pwc.component.workflow.dao.IFlowEventDAO;
import com.pwc.component.workflow.dao.IFlowPointDAO;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public class FlowPointExDao implements IFlowPointExDao {

    @Autowired
    private IRolePointDao rolePointDao;


    @Autowired
    private IFlowPointDAO flowPointDAO;

    @Autowired
    private IFlowEventDAO flowEventDAO;

    @Override
    public FlowPointEx getFlowPointExByPointId(FlowPoint flowPoint) {

        FlowPointEx flowPointEx = new FlowPointEx();
        flowPointEx.setFlowPoint(flowPoint);
        List<FlowEvent> flowEvents =flowEventDAO.findByPointId(flowPoint.getFlowPointId());
        flowPointEx.setPossibleEvents(flowEvents);
        RolePoint rp= rolePointDao.findRolePointByPointId( flowPoint.getFlowPointId());
        flowPointEx.setRoleId(rp.getRoleId());
        return flowPointEx;
    }

    @Override
    public FlowPointEx getFlowPointExByPointId(String flowPointId) {
        FlowPoint flowPoint=flowPointDAO.find(flowPointId);
        return getFlowPointExByPointId(flowPoint);
    }

}
