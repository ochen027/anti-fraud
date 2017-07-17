package com.pwc.aml.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.common.base.entity.BaseEntity;

@Entity
@Table(name="USERGROUP")
public class UserGroup extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="USER_ID")
	private int userId;
	@Column(name="GROUP_ID")
	private int groupId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	
	
	
}
