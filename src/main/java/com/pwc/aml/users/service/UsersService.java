package com.pwc.aml.users.service;

import com.pwc.aml.users.dao.IUsersDAO;
import com.pwc.aml.users.entity.UserGroupRoleBean;
import com.pwc.aml.users.entity.Users;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ochen027 on 7/3/2017.
 */
@Service
public class UsersService implements IUsersService {

    @Autowired
    private IUsersDAO usersDAO;

    @Override
    public Users checkUserName(Users users) {
        return usersDAO.checkUserName(users);
    }

    @Override
    public List<UserGroupRoleBean> fetchUserGroup(int userId) {
        return usersDAO.fetchUserGroups(userId);
    }

    @Override
    public List<UserGroupRoleBean> fetchGroupRole(int groupId) {
        return usersDAO.fetchGroupRoles(groupId);
    }

}
