package com.pwc.aml.users.dao;

import com.pwc.aml.users.entity.Users;

import java.util.List;

public interface IUsersDAO {

    public Users checkUserName(Users users);

    public List fetchUserGroups(int userId);

    public List fetchGroupRoles(int groupId);

    public void addNewUser(Users users);

    public void deleteUser(int userId);

    public void updateUser(Users users);

    public List<Users> listAllUsers();
    
    public Users findUserByUserId(int userId);

}
