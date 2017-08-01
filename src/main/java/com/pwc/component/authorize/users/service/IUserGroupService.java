package com.pwc.component.authorize.users.service;

import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.entity.Users;

import java.util.List;

public interface IUserGroupService {

    List<UserGroup> getAllGroupsByUser(int userId);

    UserGroup createUserGroup(UserGroup ug, String userName);

    UserGroup RemoveUserGroup(UserGroup ug, String userName);

    void createUserGroup(Users u, List<UserGroup> lug, String userName);


}
