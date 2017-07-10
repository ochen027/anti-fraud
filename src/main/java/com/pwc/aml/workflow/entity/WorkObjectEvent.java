package com.pwc.aml.workflow.entity;

import com.pwc.aml.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="Workflow")
public class WorkObjectEvent extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="FLOW_ID")
    private int flowId;

    @Column(name="CHART_JSON")
    private String chartJson;

    @Column(name="NAME")
    private String name;

    @Column(name="TYPE")
    private String type;

    @Column(name="DESCRIPTION")
    private String description;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
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
}
