package com.pwc.component.authorize.roles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.common.base.entity.BaseEntity;

@Entity
@Table(name="ROLEMENU")
public class RoleMenu extends BaseEntity{
	

	private static final long serialVersionUID = 1L;
	@Column(name="ROLE_ID")
	private int roleId;
	@Column(name="MENU_ID")
	private int menuId;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

}
