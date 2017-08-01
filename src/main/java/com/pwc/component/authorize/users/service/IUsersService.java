package com.pwc.component.authorize.users.service;

import java.util.List;

import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.component.authorize.roles.entity.Roles;
import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.entity.Users;


public interface IUsersService {

	Users checkUserName(Users users);

	List<Groups> fetchUserGroup(int userId);

	List<Roles> fetchGroupRole(int groupId);

	List<Users> listAllUsers();

	void createUser(Users u, String userName);

	void updateUser(Users u, String userName);

	void deleteUser(int userId, String userName);

	Users findSingleUser(int userId);

	void addUserIntoGroup(UserGroup ug, String userName);

	void updateUserGroup(UserGroup ug, String userName);

	void deleteUserFromGroup(int id, String userName);

	UserGroup getUserGroupRelationship(int id);

	Users saveOrUpdate(Users u,String userName);

	List<Users> searchUsersByConditions(int userId, String userName);
}
