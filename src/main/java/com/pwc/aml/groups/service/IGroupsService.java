package com.pwc.aml.groups.service;

import java.util.List;

import com.pwc.aml.groups.entity.Groups;

public interface IGroupsService {

    List<Groups> listAllGroups();
     
    void createGroups(Groups g);

    void updateGroups(Groups g);

    void deleteGroups(int groupId);

    Groups getSingleGroups(int groupId);
}
