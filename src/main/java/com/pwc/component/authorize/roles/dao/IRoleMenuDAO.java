package com.pwc.component.authorize.roles.dao;

;
import com.pwc.component.authorize.roles.entity.RoleMenu;

import java.util.List;

public interface IRoleMenuDAO {

    List<RoleMenu> getRoleMenuByRole(int roleId);

    RoleMenu createRoleMenu(RoleMenu rm);

    void updateRoleMenu(RoleMenu rm);

    RoleMenu RemoveRoleMenu(RoleMenu rm);

    RoleMenu getRoleMenuById(int id);

    RoleMenu getRoleMenu(int roleId, int menuId);
    List<RoleMenu> getRoleMenu();
}
