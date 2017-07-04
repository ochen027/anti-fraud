package com.pwc.aml.groups.service;

import com.pwc.aml.groups.entity.Groups;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */

public interface IGroupsService {

    public List<Groups> listAllGroups();

    public boolean createGroups(Groups g);

    public boolean updateGroups(int groupId);

    public boolean deleteGroups(int groupId);

    public Groups getSingleGroups(int groupId);
}
