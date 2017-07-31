package com.pwc.component.authorize.groups.dao;

import java.util.List;

import com.pwc.component.authorize.groups.entity.Groups;

public interface IGroupsDAO {
	
    List<Groups> listAllGroups();
    
    Groups createGroups(Groups ug);

    Groups updateGroups(Groups ug);
    
    Groups getGroups(int groupId);
}
