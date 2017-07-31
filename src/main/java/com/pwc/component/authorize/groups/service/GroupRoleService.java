package com.pwc.component.authorize.groups.service;

import com.pwc.component.authorize.groups.dao.IGroupRoleDAO;
import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.groups.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by iwen005 on 7/31/2017.
 */
@Service
public class GroupRoleService implements IGroupRoleService {

    @Autowired
    IGroupRoleDAO groupRoleDAO;

    @Override
    public List<GroupRole> getAllRolesByGroup(int groupId) {
        return groupRoleDAO.getAllRolesByGroup(groupId);
    }

    @Override
    public GroupRole createGroupRole(GroupRole gr, String userName) {
        return groupRoleDAO.createGroupRole(gr);
    }

    @Override
    public void createGroupRole(Groups g, List<GroupRole> lgr,String userName) {
        for(int i=0; i<lgr.size(); i++){
            GroupRole gr = (GroupRole) lgr.get(i);
            //get groupRoleBy id

            GroupRole tempGr = groupRoleDAO.getGroupRole(gr.getGroupId(),gr.getRoleId());
            if(tempGr == null){
                gr.setGroupId(g.getId());
                gr.setCreatedBy(userName);
                gr.setCreationDate(new Date());
                gr.setLastUpdateDate(new Date());
                gr.setLastUpdatedBy(userName);
                gr.setStatus(true);
                this.createGroupRole(gr,userName);
            }else{
                //if exist, do nothing
            }

        }

    }


    @Override
    public GroupRole RemoveGroupRole(GroupRole gr, String userName) {
        return groupRoleDAO.RemoveGroupRole(gr);
    }
}
