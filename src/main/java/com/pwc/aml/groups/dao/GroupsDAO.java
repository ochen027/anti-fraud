package com.pwc.aml.groups.dao;

import com.pwc.aml.groups.entity.Groups;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */
@Transactional
@Repository
public class GroupsDAO implements IGroupsDAO {

    @Override
    public List<Groups> listAllGroups() {
        return null;
    }

    @Override
    public boolean createGroups(Groups ug) {
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
    public Groups getGroups(int groupId) {
        return null;
    }
}
