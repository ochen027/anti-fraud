package com.pwc.component.authorize.groups.service;

import java.util.List;

import com.pwc.component.authorize.groups.entity.Groups;

public interface IGroupsService {

    List<Groups> listAllGroups();
     
    void createGroups(Groups g, String userName);

    void updateGroups(Groups g, String userName);

    void deleteGroups(int groupId, String userName);

    Groups getSingleGroups(int groupId);
}
