package com.pwc.aml.workflow.entity;

import javax.persistence.*;

import com.pwc.aml.base.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name="FlowPoint")
public class FlowPoint extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="POINT_ID")
    private int pointId;

    @Column(name="TYPE")
    private String type;

    @Column(name="NAME")
    private String name;

    @Column(name="PRINT")
    private String print;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="FLOW_ID")
//    private Workflow workflow;

    @Column(name="DESCRIPTION")
    private String description;

//    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "eventId")
//    private List<FlowEvent> lflowEvents;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
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

//    public Workflow getWorkflow() {
//        return workflow;
//    }
//
//    public void setWorkflow(Workflow workflow) {
//        this.workflow = workflow;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<FlowEvent> getLflowEvents() {
//        return lflowEvents;
//    }
//
//    public void setLflowEvents(List<FlowEvent> lflowEvents) {
//        this.lflowEvents = lflowEvents;
//    }
}
