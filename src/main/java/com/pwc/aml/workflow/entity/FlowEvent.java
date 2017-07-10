package com.pwc.aml.workflow.entity;

import javax.persistence.*;

import com.pwc.aml.base.entity.BaseEntity;

@Entity
@Table(name="FlowEvent")
public class FlowEvent extends BaseEntity {


	private static final long serialVersionUID = 1L;



    @Column(name="TYPE")
	private String type;

    @Column(name="NAME")
    private String name;

    @Column(name="PRINT")
    private String print;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FLOW_ID")
    private Workflow workflow;

    @Column(name="DESCRIPTION")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENDPOINT")
    private FlowPoint endPoint;

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

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FlowPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(FlowPoint endPoint) {
        this.endPoint = endPoint;
    }
}
