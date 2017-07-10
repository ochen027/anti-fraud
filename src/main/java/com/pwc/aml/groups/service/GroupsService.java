package com.pwc.aml.groups.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.groups.dao.IGroupsDAO;
import com.pwc.aml.groups.entity.Groups;

@Service
public class GroupsService implements IGroupsService {

    @Autowired
    private IGroupsDAO groupsDAO;

    @Override
    public List<Groups> listAllGroups() {
        return groupsDAO.listAllGroups();
    }

	@Override
	public void createGroups(Groups g) {
		groupsDAO.createGroups(g);
	}

	@Override
	public void updateGroups(Groups g) {
		groupsDAO.updateGroups(g);
	}

	@Override
	public void deleteGroups(int groupId) {
		groupsDAO.deleteGroups(groupId);
	}

	@Override
	public Groups getSingleGroups(int groupId) {
		return groupsDAO.getGroups(groupId);
	}

}
