package com.pwc.component.authorize.roles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.component.authorize.roles.dao.IRolesDAO;
import com.pwc.component.authorize.roles.entity.Roles;

@Transactional
@Repository
public class RolesService implements IRolesService {

    @Autowired
    private IRolesDAO rolesDAO;



    @Override
    public List<Roles> listAllRoles() {
        return rolesDAO.listAll();
    }

    @Override
    public Roles createRoles(Roles r) {
        rolesDAO.createRoles(r);
        return r;
    }

    @Override
    public void updateRoles(Roles r) {
        rolesDAO.updateRoles(r);
    }

    @Override
    public void deleteRoles(Roles role) {

        rolesDAO.deleteRoles(role);

    }

    @Override
    public Roles getSingleRoles(int roleId) {
        return rolesDAO.getRoles(roleId);
    }
}
