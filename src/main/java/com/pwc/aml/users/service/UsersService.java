package com.pwc.aml.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.dao.IUsersDAO;
import com.pwc.aml.users.entity.Users;

/**
 * Created by ochen027 on 7/3/2017.
 */
@Service
public class UsersService implements IUsersService {

    @Autowired
    private IUsersDAO usersDAO;

    @Override
    public Users checkUserName(Users users) {
        return usersDAO.checkUserName(users);
    }

    @Override
    public List<Groups> fetchUserGroup(int userId) {
        return usersDAO.fetchUserGroups(userId);
    }

    @Override
    public List<Roles> fetchGroupRole(int groupId) {
        return usersDAO.fetchGroupRoles(groupId);
    }

	@Override
	public List<Users> listAllUsers() {
		return usersDAO.listAllUsers();
	}

	@Override
	public void createUser(Users u) {
		usersDAO.addNewUser(u);
	}

	@Override
	public void updateUser(Users u) {
		usersDAO.updateUser(u);
	}

	@Override
	public void deleteUser(int userId) {
		usersDAO.deleteUser(userId);
	}

	@Override
	public Users findSingleUser(int userId) {
		return usersDAO.findUserByUserId(userId);
	}



}
