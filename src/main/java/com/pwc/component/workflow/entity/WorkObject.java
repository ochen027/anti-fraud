package com.pwc.component.workflow.entity;

import javax.persistence.*;

import com.pwc.aml.common.base.entity.BaseEntity;

@Entity
@Table(name="WorkObject")
public class WorkObject extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="WORKOBJECT_ID")
    private int workObjId;




}
