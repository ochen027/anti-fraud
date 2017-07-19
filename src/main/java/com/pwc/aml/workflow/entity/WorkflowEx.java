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

    @Transient
    private List<FlowPointEx> flowPointsEx;

    public List<FlowPointEx> getFlowPointsEx() {
        return flowPointsEx;
    }

    public void setFlowPointsEx(List<FlowPointEx> flowPointsEx) {
        this.flowPointsEx = flowPointsEx;
    }
}
