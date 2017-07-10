package com.pwc.aml.workflow.entity;

import com.pwc.aml.entity.BaseEntity;

import javax.persistence.*;

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
