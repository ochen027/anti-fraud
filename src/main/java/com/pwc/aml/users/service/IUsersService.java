package com.pwc.aml.users.service;

import com.pwc.aml.users.entity.UserGroupRoleBean;
import com.pwc.aml.users.entity.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by ochen027 on 7/3/2017.
 */
public interface IUsersService {

    public Users checkUserName(Users users);

    public List<UserGroupRoleBean> fetchUserGroup(int userId);

    public List<UserGroupRoleBean> fetchGroupRole(int groupId);
}
