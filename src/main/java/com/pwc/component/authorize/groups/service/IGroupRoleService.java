package com.pwc.component.authorize.groups.service;

import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.groups.entity.Groups;

import java.util.List;

public interface IGroupRoleService {

    List<GroupRole> getAllRolesByGroup(int groupId);

    GroupRole createGroupRole(GroupRole gr, String userName);

    GroupRole RemoveGroupRole(GroupRole gr, String userName);

    void createGroupRole(Groups g, List<GroupRole> lgr,String userName);


}
