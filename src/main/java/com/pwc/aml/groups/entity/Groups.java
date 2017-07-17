package com.pwc.aml.groups.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.common.base.entity.BaseEntity;


@Entity
@Table(name="GROUPS")
public class Groups extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Column(name="GROUP_NAME")
    private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    

}
