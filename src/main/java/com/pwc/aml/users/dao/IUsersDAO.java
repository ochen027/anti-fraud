package com.pwc.aml.users.dao;

import com.pwc.aml.users.entity.UserGroupRoleBean;
import com.pwc.aml.users.entity.Users;

import java.util.List;
import java.util.Map;

public interface IUsersDAO {

    public Users checkUserName(Users users);

    public List<UserGroupRoleBean> fetchUserGroups(int userId);

    public List<UserGroupRoleBean> fetchGroupRoles(int groupId);

    public void addNewUser(Users users);

    public void deleteUser(String userId);

    public void updateUser(Users users);

    public List<Users> listAllUsers();

}
