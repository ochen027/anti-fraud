package com.pwc.aml.workflow.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.Workflow;

import java.util.List;

public class WorkObj {

    private String workObjId;
    private Alerts alerts;
    private FlowPointEx currentPoint;
    private List<FlowEvent> HistoryEvents;
    private String flowId;
    private WorkflowEx workflowEx;

    public String getWorkObjId() {
        return workObjId;
    }

    public void setWorkObjId(String workObjId) {
        this.workObjId = workObjId;
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }

    public FlowPointEx getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(FlowPointEx currentPoint) {
        this.currentPoint = currentPoint;
    }

    public List<FlowEvent> getHistoryEvents() {
        return HistoryEvents;
    }

    public void setHistoryEvents(List<FlowEvent> historyEvents) {
        HistoryEvents = historyEvents;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    @JsonIgnore
    public WorkflowEx getWorkflowEx() {
        return workflowEx;
    }

    public void setWorkflowEx(WorkflowEx workflowEx) {
        this.workflowEx = workflowEx;
    }
}
