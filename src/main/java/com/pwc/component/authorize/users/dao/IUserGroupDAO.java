package com.pwc.component.authorize.users.dao;

;
import com.pwc.component.authorize.users.entity.UserGroup;

import java.util.List;

/**
 * Created by iwen005 on 7/31/2017.
 */
public interface IUserGroupDAO {

    List<UserGroup> getUserGroupByUser(int userId);

    UserGroup createUserGroup(UserGroup ug);

    void updateUserGroup(UserGroup ug);

    UserGroup RemoveUserGroup(UserGroup ug);

    UserGroup getUserGroupById(int id);

    UserGroup getUserGroup(int userId, int groupId);
}
