package com.pwc.aml.workflow.entity;

import javax.persistence.*;

import com.pwc.aml.base.entity.BaseEntity;

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
