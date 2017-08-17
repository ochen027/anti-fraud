package com.pwc.aml.workflow.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.common.base.entity.BaseEntity;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.Workflow;

import java.util.List;

public class WorkObj extends BaseEntity{

    private String workObjId;
    private Alerts alerts;
    private FlowPointEx currentPoint;
    private List<FlowEvent> HistoryEvents;
    private String flowId;
    private WorkflowEx workflowEx;
    private String customerId;
    private Double totalAmt;
    private boolean IsSAR=false;
    private String accountId;
    private String transIdArray;
    private String scenarioId;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }


    public boolean isSAR() {
        return IsSAR;
    }

    public void setSAR(boolean SAR) {
        IsSAR = SAR;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransIdArray() {
        return transIdArray;
    }

    public void setTransIdArray(String transIdArray) {
        this.transIdArray = transIdArray;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }
}
