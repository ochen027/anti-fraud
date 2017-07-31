package com.pwc.component.authorize.groups.dao;

import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.groups.entity.Groups;

import java.util.List;

public interface IGroupRoleDAO {
	
    List<GroupRole> getAllRolesByGroup(int groupId);
    
    GroupRole createGroupRole(GroupRole gr);

    void updateGroupRole(GroupRole gr);

    GroupRole RemoveGroupRole(GroupRole gr);

    GroupRole getGroupRoleById(int id);

    GroupRole getGroupRole(int groupId, int roleId);
    

}
