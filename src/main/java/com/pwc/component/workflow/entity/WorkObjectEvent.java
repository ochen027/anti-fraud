package com.pwc.component.workflow.entity;

import javax.persistence.*;

import com.pwc.common.base.entity.BaseEntity;

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
