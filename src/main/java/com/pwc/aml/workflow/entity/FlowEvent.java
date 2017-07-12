package com.pwc.aml.workflow.entity;

import javax.persistence.*;

import com.pwc.aml.base.entity.BaseEntity;

@Entity
@Table(name="FlowEvent")
public class FlowEvent extends BaseEntity {


	private static final long serialVersionUID = 1L;


    @Column(name="FLOWEVENTID")
    private String flowEventId;

    @Column(name="FLOWPOINTID")
    private String flowPointId;

    @Column(name="TYPE")
	private String type;

    @Column(name="NAME")
    private String name;

    @Column(name="PRINT")
    private String print;

    @Column(name="FLOWID")
    private String flowId;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="endpoint")
    private String endpoint;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getFlowEventId() {
        return flowEventId;
    }

    public void setFlowEventId(String flowEventId) {
        this.flowEventId = flowEventId;
    }

    public String getFlowPointId() {
        return flowPointId;
    }

    public void setFlowPointId(String flowPointId) {
        this.flowPointId = flowPointId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
