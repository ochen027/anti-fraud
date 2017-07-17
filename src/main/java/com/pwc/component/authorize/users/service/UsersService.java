package com.pwc.component.authorize.users.service;

import java.util.List;

import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.dao.IUsersDAO;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.component.authorize.roles.entity.Roles;

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

	@Override
	public void addUserIntoGroup(UserGroup ug, String userName) {
		usersDAO.addUserIntoGroup(ug,userName);
	}

	@Override
	public void updateUserGroup(UserGroup ug, String userName) {
		usersDAO.updateUserGroup(ug,userName);
	}

	@Override
	public void deleteUserFromGroup(int id, String userName) {
		usersDAO.deleteUserFromGroup(id,userName);
	}

	@Override
	public UserGroup getUserGroupRelationship(int id) {
		return usersDAO.getUserGroupRelationship(id);
	}


}
