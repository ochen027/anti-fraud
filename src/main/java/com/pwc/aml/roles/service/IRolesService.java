package com.pwc.aml.roles.service;

import java.util.List;

import com.pwc.aml.roles.entity.Roles;


public interface IRolesService {
	
    List<Roles> listAllRoles();
    boolean createRoles(Roles roles);
    boolean updateRoles(int roleId);
    boolean deleteRoles(int rolesId);
    Roles getSingleRoles(int roleId);
    
}
