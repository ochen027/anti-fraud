package com.pwc.aml.groups.service;

import com.pwc.aml.groups.entity.Groups;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */

public interface IGroupsService {

    List<Groups> listAllGroups();
     
    void createGroups(Groups g);

    void updateGroups(Groups g);

    void deleteGroups(int groupId);

    Groups getSingleGroups(int groupId);
}
