package com.pwc.component.authorize.roles.service;

import java.util.List;

import com.pwc.component.authorize.roles.entity.Roles;


public interface IRolesService {
	
    List<Roles> listAllRoles();
    Roles createRoles(Roles roles);
    void updateRoles(Roles roles);
    void deleteRoles(Roles roleId);
    Roles getSingleRoles(int roleId);
    
}
