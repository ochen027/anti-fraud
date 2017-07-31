package com.pwc.component.authorize.groups.entity;

import com.pwc.common.base.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="GROUPROLE")
public class GroupRole extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="GROUP_ID")
	private int groupId;
	@Column(name="ROLE_ID")
	private int roleId;


	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


}
