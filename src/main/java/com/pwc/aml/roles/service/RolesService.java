package com.pwc.aml.roles.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.roles.entity.Roles;

/**
 * Created by ochen027 on 7/4/2017.
 */
@Transactional
@Repository
public class RolesService implements IRolesService {

    @Override
    public List<Roles> listAllRoles() {
        return null;
    }

    @Override
    public boolean createRoles(Roles roles) {
        return false;
    }

    @Override
    public boolean updateRoles(int roleId) {
        return false;
    }

    @Override
    public boolean deleteRoles(int rolesId) {
        return false;
    }

    @Override
    public Roles getSingleRoles(int roleId) {
        return null;
    }
}
