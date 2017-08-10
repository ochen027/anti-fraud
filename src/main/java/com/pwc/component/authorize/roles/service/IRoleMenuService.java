package com.pwc.component.authorize.roles.service;

import com.pwc.component.authorize.roles.entity.RoleMenu;
import com.pwc.component.authorize.roles.entity.Roles;

import java.util.List;

public interface IRoleMenuService {

    List<RoleMenu> getRoleMenuByRole(int roleId);

    RoleMenu createRoleMenu(RoleMenu rm, String roleName);

    RoleMenu RemoveRoleMenu(RoleMenu rm, String roleName);

    void createRoleMenu(Roles r, List<RoleMenu> lrm, String roleName);
    List<RoleMenu> getRoleMenu();

}
