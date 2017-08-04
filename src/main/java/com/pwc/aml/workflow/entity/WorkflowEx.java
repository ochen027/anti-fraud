package com.pwc.aml.workflow.entity;


import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;
import com.pwc.component.workflow.entity.Workflow;


import javax.persistence.Transient;
import java.util.List;


public class WorkflowEx extends Workflow {


    public WorkflowEx() {
        super();
    }

    public Workflow getWorkflow() {
        return new Workflow(this.getFlowId(), this.getChartJson(), this.getName(), this.getType(),
                this.getDescription(), this.getFlowPoints(), this.getFlowEvents());
    }

    public WorkflowEx(Workflow workflow) {
        super();
        this.setFlowId(workflow.getFlowId());
        this.setChartJson(workflow.getChartJson());
        this.setName(workflow.getName());
        this.setType(workflow.getType());
        this.setDescription(workflow.getDescription());
        this.setFlowPoints(workflow.getFlowPoints());
        this.setFlowEvents(workflow.getFlowEvents());
    }

    @Transient
    private List<FlowPointEx> flowPointsEx;

    public List<FlowPointEx> getFlowPointsEx() {
        return flowPointsEx;
    }

    public void setFlowPointsEx(List<FlowPointEx> flowPointsEx) {
        this.flowPointsEx = flowPointsEx;
    }


    public FlowPointEx getStartPoint() {
        return getPointByType("WorkflowShape.WorkflowStart");
    }

    public FlowPointEx getEndPoint() {
        return getPointByType("WorkflowShape.WorkflowEnd");
    }

    public FlowPointEx getPointByType(String type){
        for (FlowPointEx flowPoint : flowPointsEx) {
            if(flowPoint.getType().equals(type))
                return flowPoint;
        }
        return null;
    }

}
