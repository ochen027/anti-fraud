package com.pwc.aml.workflow.entity;

import com.pwc.aml.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="WorkObjectEvent")
public class WorkObjectEvent extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="FLOW_ID")
    private int flowId;


}
