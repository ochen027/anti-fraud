package com.pwc.aml.groups.service;

import com.pwc.aml.groups.dao.IGroupsDAO;
import com.pwc.aml.groups.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */
@Service
public class GroupsService implements IGroupsService {

    @Autowired
    private IGroupsDAO groupsDAO;

    @Override
    public List<Groups> listAllGroups() {
        return null;
    }

    @Override
    public boolean createGroups(Groups g) {
        return false;
    }

    @Override
    public boolean updateGroups(int groupId) {
        return false;
    }

    @Override
    public boolean deleteGroups(int groupId) {
        return false;
    }

    @Override
    public Groups getSingleGroups(int groupId) {
        return null;
    }
}
