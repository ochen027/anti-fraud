package com.pwc.aml.users.dao;

import java.util.List;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.entity.Users;

public interface IUsersDAO {

     Users checkUserName(Users users);

     List<Groups> fetchUserGroups(int userId);

     List<Roles> fetchGroupRoles(int groupId);

     void addNewUser(Users users);

     void deleteUser(int userId);

     void updateUser(Users users);

     List<Users> listAllUsers();
    
     Users findUserByUserId(int userId);

}
