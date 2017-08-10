package com.pwc.component.authorize.roles.dao;

import java.util.List;

import com.pwc.component.authorize.roles.entity.RoleMenu;
import com.pwc.component.authorize.roles.entity.Roles;

public interface IRolesDAO {
	Roles checkRoleName(Roles roles);
	List<Roles> listAll();
	//List<Roles> fetchRoleMenu(int roleId);

	Roles createRoles(Roles ur);

	Roles updateRoles(Roles ur);

	void deleteRoles(Roles ur);

	Roles getRoles(int roleId);
	void addRoleIntoMenu(RoleMenu rm, String roleMenu);

	void updateRoleMenu(RoleMenu rm, String roleName);

	void deleteMenuFromRole(int id, String roleName);

	RoleMenu getRoleMenuRelationship(int id);

}
