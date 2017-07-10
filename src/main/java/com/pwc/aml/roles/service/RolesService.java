package com.pwc.aml.roles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.roles.dao.IRolesDAO;
import com.pwc.aml.roles.entity.Roles;

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
