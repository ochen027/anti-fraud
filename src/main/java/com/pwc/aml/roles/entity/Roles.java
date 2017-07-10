package com.pwc.aml.roles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.aml.entity.BaseEntity;

@Entity
@Table(name="ROLES")
public class Roles extends BaseEntity{


	private static final long serialVersionUID = 1L;
	@Column(name="ROLE_NAME")
    private String roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
