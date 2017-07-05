package com.pwc.aml.users.service;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.entity.Users;

import java.util.*;

/**
 * Created by ochen027 on 7/3/2017.
 */
public interface IUsersService {

    public Users checkUserName(Users users);

    public List<Groups> fetchUserGroup(int userId);

    public List<Roles> fetchGroupRole(int groupId);


}
