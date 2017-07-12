package com.pwc.aml.workflow.entity;

import javax.persistence.*;

import com.pwc.aml.base.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name="Workflow")
public class Workflow extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    @Column(name="FLOWID")
    private String flowId;

    @Column(name="CHART_JSON",columnDefinition="CLOB NOT NULL")
    private String chartJson;

    @Column(name="NAME")
    private String name;

    @Column(name="TYPE")
    private String type;

    @Column(name="DESCRIPTION")
    private String description;

    @Transient
    private List<FlowPoint> flowPoints;

    @Transient
    private List<FlowEvent> flowEvents;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getChartJson() {
        return chartJson;
    }

    public void setChartJson(String chartJson) {
        this.chartJson = chartJson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }


    public List<FlowPoint> getFlowPoints() {
        return flowPoints;
    }

    public void setFlowPoints(List<FlowPoint> flowPoints) {
        this.flowPoints = flowPoints;
    }

    public List<FlowEvent> getFlowEvents() {
        return flowEvents;
    }

    public void setFlowEvents(List<FlowEvent> flowEvents) {
        this.flowEvents = flowEvents;
    }
}
