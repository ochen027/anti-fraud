package com.pwc.component.authorize.groups.service;

import java.util.Date;
import java.util.List;

import com.pwc.component.authorize.groups.dao.IGroupRoleDAO;
import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.groups.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.component.authorize.groups.dao.IGroupsDAO;

@Service
public class GroupsService implements IGroupsService {

    @Autowired
    private IGroupsDAO groupsDAO;

    @Autowired
	private IGroupRoleDAO groupRoleDAO;

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
		List<GroupRole> grs = groupRoleDAO.getAllRolesByGroup(groupId);
		if(grs.size() > 0 ){
			for(int i = 0; i<grs.size(); i++){
				GroupRole gr = (GroupRole)grs.get(i);
				gr.setStatus(false);
				gr.setLastUpdatedBy(userName);
				gr.setLastUpdateDate(new Date());
				groupRoleDAO.updateGroupRole(gr);
			}
		}
	}

	@Override
	public Groups getSingleGroups(int groupId) {

    	return groupsDAO.getGroups(groupId);
	}

	@Override
	public Groups saveOrUpdate(Groups g,String userName) {
    	Groups result = null;
		Groups group = groupsDAO.getGroups(g.getId());
		if(group == null){
			g.setLastUpdatedBy(userName);
			g.setLastUpdateDate(new Date());
			g.setCreatedBy(userName);
			g.setCreationDate(new Date());
			g.setStatus(true);
			result = groupsDAO.createGroups(g);
		}else{
			g.setLastUpdateDate(new Date());
			g.setLastUpdatedBy(userName);
			result = groupsDAO.updateGroups(g);
		}
		return result;
	}


}
