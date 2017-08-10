package com.pwc.component.authorize.roles.service;

import java.util.List;

import com.pwc.component.authorize.roles.entity.RoleMenu;
import com.pwc.component.authorize.roles.entity.Roles;


public interface IRolesService {
    Roles checkRoleName(Roles roles);
    List<Roles> listAllRoles();
    Roles createRoles(Roles roles);
    void updateRoles(Roles roles);
    void deleteRoles(Roles roleId);
    Roles getSingleRoles(int roleId);
    void addRoleIntoMenu(RoleMenu rm, String roleName);

    void updateRoleMenu(RoleMenu rm, String roleName);

    void deleteMenuFromRole(int id, String roleName);

    RoleMenu getRoleMenuRelationship(int id);
    
}
