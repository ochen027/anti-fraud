package com.pwc.component.authorize.roles.dao;

import java.util.List;

import com.pwc.component.authorize.roles.entity.Roles;

public interface IRolesDAO {
	
	List<Roles> listAll();

	Roles createRoles(Roles ur);

	void updateRoles(Roles ur);

	void deleteRoles(Roles ur);

	Roles getRoles(int roleId);
}
