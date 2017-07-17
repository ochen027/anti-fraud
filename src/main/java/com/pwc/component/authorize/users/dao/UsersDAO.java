package com.pwc.component.authorize.users.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pwc.component.authorize.users.entity.UserGroup;
import com.pwc.component.authorize.users.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.component.authorize.roles.entity.Roles;

@Transactional
@Repository
public class UsersDAO implements IUsersDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public Users checkUserName(Users users) {
        String hql = "FROM Users WHERE userName = ? and status = 1";
        List<Users> uList = (List<Users>)entityManager.createQuery(hql).setParameter(1, users.getUserName()).getResultList();
        entityManager.clear();
        Users u = null;
        if(1 == uList.size()){
        	u = uList.get(0);
        }
        return u;
    }

	@Override
    public List<Groups> fetchUserGroups(int userId) {
        String hql = "SELECT G.id, G.groupName FROM Users AS U, Groups AS G, UserGroup AS UG" +
                " WHERE U.id = UG.userId AND G.id = UG.groupId AND U.status = 1 AND G.status = 1 AND U.id = ?";
        List rlist = entityManager.createQuery(hql).setParameter(1, userId).getResultList();
        Iterator iter = rlist.iterator();
        List<Groups> groupList = new ArrayList<Groups>(); 
        while(iter.hasNext()){
        	Object[] obj = (Object[]) iter.next();
        	Groups g = new Groups();
        	g.setId(null == obj[0] ? 0 : (Integer)obj[0]);
        	g.setGroupName((String)obj[1]);
        	groupList.add(g);
        }
        
        return groupList;
    }

	@Override
    public List<Roles> fetchGroupRoles(int groupId) {
        String hql = "SELECT R.id, R.roleName FROM Roles AS R, Groups AS G, GroupRole AS GR" +
                " WHERE R.id = GR.roleId AND G.id = GR.groupId AND R.status = 1 AND G.status = 1 AND G.id = ?";
        List rlist = entityManager.createQuery(hql).setParameter(1, groupId).getResultList();
        Iterator iter = rlist.iterator();
        List<Roles> roleList = new ArrayList<Roles>(); 
        while(iter.hasNext()){
        	Object[] obj = (Object[]) iter.next();
        	Roles r = new Roles();
        	r.setId(null == obj[0] ? 0 : (Integer)obj[0]);
        	r.setRoleName((String)obj[1]);
        	roleList.add(r);
        }
        return roleList;
    }

    @Override
    public void addNewUser(Users users, String userName) {
        users.setCreatedBy(userName);
        users.setCreationDate(new Date());
        users.setLastUpdatedBy(userName);
        entityManager.persist(users);
    }

    @Override
    public void deleteUser(int userId, String userName) {
        Users u = this.findUserByUserId(userId);
        u.setStatus(false);
        entityManager.flush();
    	//entityManager.remove(this.findUserByUserId(userId));
    }

    @Override
    public void updateUser(Users users, String userName) {
    	Users u = this.findUserByUserId(users.getId());
    	u.setUserName(users.getUserName());
    	u.setUserPwd(users.getUserPwd());
    	u.setLastUpdatedBy(userName);
    	entityManager.flush();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Users> listAllUsers() {
    	String hql = "FROM Users ORDER BY userId";
        return (List<Users>) entityManager.createQuery(hql).getResultList();
    }

	@Override
	public Users findUserByUserId(int userId) {
		return entityManager.find(Users.class, userId);
	}

    @Override
    public void addUserIntoGroup(UserGroup ug, String userName) {
        ug.setCreatedBy(userName);
        ug.setLastUpdatedBy(userName);
        ug.setCreationDate(new Date());
        entityManager.persist(ug);
    }

    @Override
    public void updateUserGroup(UserGroup ug, String userName) {
        UserGroup uGroup = this.getUserGroupRelationship(ug.getId());
        uGroup.setLastUpdatedBy(userName);
        uGroup.setGroupId(ug.getGroupId());
        uGroup.setUserId(ug.getUserId());
        entityManager.flush();
    }

    @Override
    public void deleteUserFromGroup(int id, String userName) {
        UserGroup uGroup = this.getUserGroupRelationship(id);
        uGroup.setLastUpdatedBy(userName);
        uGroup.setStatus(false);
        entityManager.flush();
    }

    @Override
    public UserGroup getUserGroupRelationship(int id) {
        return entityManager.find(UserGroup.class, id);
    }


}
