package com.pwc.aml.groups.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void createGroups(Groups g, String userName) {
		g.setCreatedBy(userName);
		g.setLastUpdatedBy(userName);
		groupsDAO.createGroups(g);
	}

	@Override
	public void updateGroups(Groups g, String userName) {
		g.setLastUpdatedBy(userName);
		groupsDAO.updateGroups(g);
	}

	@Override
	public void deleteGroups(int groupId, String userName) {
		Groups g = groupsDAO.getGroups(groupId);
		g.setLastUpdatedBy(userName);
		g.setStatus(false);
		groupsDAO.updateGroups(g);
	}

	@Override
	public Groups getSingleGroups(int groupId) {
		return groupsDAO.getGroups(groupId);
	}

}
