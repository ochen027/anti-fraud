package com.pwc.component.authorize.groups.entity;

import com.pwc.common.base.entity.BaseEntity;
import com.pwc.component.authorize.roles.entity.Roles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="GROUPS")
public class Groups extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Column(name="GROUP_NAME")
    private String groupName;

	@Transient
    private List<GroupRole> groupRolesList = new ArrayList<GroupRole>();


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {

		this.groupName = groupName;
	}

	public List<GroupRole> getGroupRolesList() {
		return groupRolesList;
	}

	public void setGroupRolesList(List<GroupRole> groupRolesList) {
		this.groupRolesList = groupRolesList;
	}
}
