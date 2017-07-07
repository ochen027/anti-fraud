package com.pwc.aml.groups.dao;

import com.pwc.aml.groups.entity.Groups;

import java.util.List;

public interface IGroupsDAO {
	
    List<Groups> listAllGroups();
    
    void createGroups(Groups ug);
    
    void updateGroups(Groups ug);
    
    void deleteGroups(int groupId);
    
    Groups getGroups(int groupId);
}
