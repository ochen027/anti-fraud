package com.pwc.aml.users.dao;

import java.util.List;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.entity.UserGroup;
import com.pwc.aml.users.entity.Users;

public interface IUsersDAO {

     Users checkUserName(Users users);

     List<Groups> fetchUserGroups(int userId);

     List<Roles> fetchGroupRoles(int groupId);

     void addNewUser(Users users, String userName);

     void deleteUser(int userId, String userName);

     void updateUser(Users users, String userName);

     List<Users> listAllUsers();
    
     Users findUserByUserId(int userId);

     void addUserIntoGroup(UserGroup ug, String userName);

     void updateUserGroup(UserGroup ug, String userName);

     void deleteUserFromGroup(int id, String userName);

     UserGroup getUserGroupRelationship(int id);
}
