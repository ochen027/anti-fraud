package com.pwc.aml.groups.service;

import java.util.List;

import com.pwc.aml.groups.entity.Groups;

public interface IGroupsService {

    List<Groups> listAllGroups();
     
    void createGroups(Groups g, String userName);

    void updateGroups(Groups g, String userName);

    void deleteGroups(int groupId, String userName);

    Groups getSingleGroups(int groupId);
}
