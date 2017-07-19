package com.pwc.aml.workflow.entity;


import com.pwc.aml.alert.entity.Alerts;
import com.pwc.component.workflow.entity.FlowEvent;

import java.util.List;

public class WorkObj {

    private String workObjId;
    private Alerts alertId;
    private FlowPointEx currentPoint;
    private List<FlowEvent> HistoryEvents;


    public String getWorkObjId() {
        return workObjId;
    }

    public void setWorkObjId(String workObjId) {
        this.workObjId = workObjId;
    }

    public Alerts getAlertId() {
        return alertId;
    }

    public void setAlertId(Alerts alertId) {
        this.alertId = alertId;
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
}
