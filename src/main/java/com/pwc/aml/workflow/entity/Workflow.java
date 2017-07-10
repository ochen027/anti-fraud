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



    @Column(name="CHART_JSON")
    private String chartJson;

    @Column(name="NAME")
    private String name;

    @Column(name="TYPE")
    private String type;

    @Column(name="DESCRIPTION")
    private String description;


    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "id")

    private List<FlowPoint> lflowPoints;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "id")

    private List<FlowEvent> lflowEvents;



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


    public List<FlowPoint> getLflowPoints() {
        return lflowPoints;
    }

    public void setLflowPoints(List<FlowPoint> lflowPoints) {
        this.lflowPoints = lflowPoints;
    }

    public List<FlowEvent> getLflowEvents() {
        return lflowEvents;
    }

    public void setLflowEvents(List<FlowEvent> lflowEvents) {
        this.lflowEvents = lflowEvents;
    }
}
