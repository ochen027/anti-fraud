package com.pwc.aml.roles.service;

import com.pwc.aml.roles.entity.Roles;

import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */
public interface IRolesService {
    public List<Roles> listAllRoles();
    public boolean createRoles(Roles roles);
    public boolean updateRoles(int roleId);
    public boolean deleteRoles(int rolesId);
    public Roles getSingleRoles(int roleId);
}
