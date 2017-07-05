package com.pwc.aml.groups.dao;

import com.pwc.aml.groups.entity.Groups;

import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */
public interface IGroupsDAO {
    public List<Groups> listAllGroups();
    public boolean createGroups(Groups ug);
    public boolean updateGroups(int groupId);
    public boolean deleteGroups(int groupId);
    public Groups getGroups(int groupId);
}
