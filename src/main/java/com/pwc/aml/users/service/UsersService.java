package com.pwc.aml.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.dao.IUsersDAO;
import com.pwc.aml.users.entity.Users;

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
	public void createUser(Users u, String userName) {
		usersDAO.addNewUser(u, userName);
	}

	@Override
	public void updateUser(Users u, String userName) {
		usersDAO.updateUser(u, userName);
	}

	@Override
	public void deleteUser(int userId, String userName) {
		usersDAO.deleteUser(userId, userName);
	}

	@Override
	public Users findSingleUser(int userId) {
		return usersDAO.findUserByUserId(userId);
	}



}
