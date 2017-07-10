package com.pwc.aml.users.service;

import java.util.List;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.entity.Users;


public interface IUsersService {

	 Users checkUserName(Users users);
	
	 List<Groups> fetchUserGroup(int userId);
	
	 List<Roles> fetchGroupRole(int groupId);
	
	 List<Users> listAllUsers();
	
	 void createUser(Users u);
	
	 void updateUser(Users u);
	
	 void deleteUser(int userId);
	
	 Users findSingleUser(int userId);

}
