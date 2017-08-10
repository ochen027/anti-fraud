package com.pwc.component.authorize.roles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pwc.common.base.entity.BaseEntity;
import java.util.List;

@Entity
@Table(name="ROLES")
public class Roles extends BaseEntity{


	private static final long serialVersionUID = 1L;
	@Column(name="ROLE_NAME")
    private String roleName;
	@Transient
	private List<RoleMenu> roleMenuList;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<RoleMenu> getRoleMenuList() {
		return roleMenuList;
	}
	public void setRoleMenuList(List<RoleMenu> roleMenuList) {
		this.roleMenuList = roleMenuList;
	}

}

