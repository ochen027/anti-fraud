package com.pwc.component.authorize.users.dao;

import java.util.List;

import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.component.authorize.roles.entity.Roles;
import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.entity.Users;

public interface IUsersDAO {

     Users checkUserName(Users users);

     List<Groups> fetchUserGroups(int userId);

     List<Roles> fetchGroupRoles(int groupId);

     void addNewUser(Users users, String userName);

     void deleteUser(int userId, String userName);

     void updateUser(Users users, String userName);

     List<Users> listAllUsers();

     List<Users> searchUsersByConditions(int userId, String userName);
    
     Users findUserByUserId(int userId);

     void addUserIntoGroup(UserGroup ug, String userName);

     void updateUserGroup(UserGroup ug, String userName);

     void deleteUserFromGroup(int id, String userName);

     UserGroup getUserGroupRelationship(int id);

}
