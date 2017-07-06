package com.pwc.aml.users.service;

import com.pwc.aml.groups.entity.Groups;
import com.pwc.aml.roles.entity.Roles;
import com.pwc.aml.users.dao.IUsersDAO;
import com.pwc.aml.users.entity.Users;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ochen027 on 7/3/2017.
 */
@Service
public class UsersService implements IUsersService {

    @Autowired
    private IUsersDAO usersDAO;

    @Override
    public Users checkUserName(Users users) {
        return usersDAO.checkUserName(users);
    }

    @Override
    public List<Groups> fetchUserGroup(int userId) {
        List l =usersDAO.fetchUserGroups(userId);
        List<Groups> gList = new ArrayList<Groups>();
        for(Object o : l){
            Object[] array = (Object[])o;
            Groups g = new Groups();
            g.setUserGroupId((Integer)array[0]);
            g.setUserGroupName((String)array[1]);
            gList.add(g);
        }
        return gList;
    }

    @Override
    public List<Roles> fetchGroupRole(int groupId) {
        List l = usersDAO.fetchGroupRoles(groupId);
        List<Roles> rList = new ArrayList<Roles>();
        for(Object o : l){
            Object[] array = (Object[])o;
            Roles r = new Roles();
            r.setRoleId((Integer)array[0]);
            r.setRoleName((String)array[1]);
            rList.add(r);
        }
        return rList;
    }

	@Override
	public List<Users> listAllUsers() {
		return usersDAO.listAllUsers();
	}

	@Override
	public void createUser(Users u) {
		usersDAO.addNewUser(u);
	}

	@Override
	public void updateUser(Users u) {
		usersDAO.updateUser(u);
	}

	@Override
	public void deleteUser(int userId) {
		usersDAO.deleteUser(userId);
	}

	@Override
	public Users findSingleUser(int userId) {
		return usersDAO.findUserByUserId(userId);
	}



}
