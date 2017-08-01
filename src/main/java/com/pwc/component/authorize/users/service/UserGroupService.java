package com.pwc.component.authorize.users.service;

import com.pwc.component.authorize.users.dao.IUserGroupDAO;
import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserGroupService implements IUserGroupService{


    @Autowired
    IUserGroupDAO userGroupDAO;

    @Override
    public List<UserGroup> getAllGroupsByUser(int userId) {
        return userGroupDAO.getUserGroupByUser(userId);
    }

    @Override
    public UserGroup createUserGroup(UserGroup ug, String userName) {
        return userGroupDAO.createUserGroup(ug);
    }

    @Override
    public void createUserGroup(Users u, List<UserGroup> lug,String userName) {
        for(int i=0; i<lug.size(); i++){
            UserGroup ug = (UserGroup) lug.get(i);
            //get UserGroupBy id
            UserGroup tempUg = userGroupDAO.getUserGroup(ug.getUserId(),ug.getGroupId());

            if(tempUg == null){
                ug.setUserId(u.getId());
                ug.setCreatedBy(userName);
                ug.setCreationDate(new Date());
                ug.setLastUpdateDate(new Date());
                ug.setLastUpdatedBy(userName);
                ug.setStatus(true);
                this.createUserGroup(ug,userName);
            }else{
                //if exist, do nothing
            }

        }

    }

    @Override
    public UserGroup RemoveUserGroup(UserGroup ug, String userName) {
        return userGroupDAO.RemoveUserGroup(ug);
    }
}
