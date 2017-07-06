package com.pwc.aml.users.dao;

import com.pwc.aml.users.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ochen027 on 7/3/2017.
 */
@Transactional
@Repository
public class UsersDAO implements  IUsersDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Users checkUserName(Users users) {
        String sql = "SELECT USER_ID, USER_NAME, USER_PASSWORD FROM USERS WHERE USER_NAME = ? and STATUS = 1";
        List uList = entityManager.createNativeQuery(sql).setParameter(1, users.getUserName()).getResultList();
        Users u = null;
        if(uList.size() == 1){
        	for(Object o: uList){
            	Object[] obj = (Object[])o;
            	u = new Users();
            	u.setUserId(obj[0]==null ? 0 : (Integer)obj[0]);
            	u.setUserName((String)obj[1]);
            	u.setUserPwd((String)obj[2]);
            }
        }
        return u;
    }

    @Override
    public List fetchUserGroups(int userId) {
        String sql = "SELECT DISTINCT G.GROUP_ID, G.GROUP_NAME " +
                "FROM USERS AS U, GROUPS AS G, USERGROUP AS UG" +
                " WHERE U.USER_ID = UG.USER_ID AND G.GROUP_ID = UG.GROUP_ID AND U.STATUS = 1 AND G.STATUS = 1 AND U.USER_ID = ?";
        return entityManager.createNativeQuery(sql).setParameter(1, userId).getResultList();
    }

    @Override
    public List fetchGroupRoles(int groupId) {
        String sql = "SELECT DISTINCT R.ROLE_ID, R.ROLE_NAME " +
                "FROM ROLES AS R, GROUPS AS G, GROUPROLE AS GR" +
                " WHERE R.ROLE_ID = GR.ROLE_ID AND G.GROUP_ID = GR.GROUP_ID AND R.STATUS = 1 AND G.STATUS = 1 AND G.GROUP_ID = ?";
        return entityManager.createNativeQuery(sql).setParameter(1, groupId).getResultList();
    }

    @Override
    public void addNewUser(Users users) {
    	entityManager.persist(users);
    }

    @Override
    public void deleteUser(int userId) {
    	entityManager.remove(this.findUserByUserId(userId));
    }

    @Override
    public void updateUser(Users users) {
    	Users u = this.findUserByUserId(users.getUserId());
    	u.setUserName(users.getUserName());
    	u.setUserPwd(users.getUserPwd());
    	entityManager.flush();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Users> listAllUsers() {
    	String hql = "FROM USERS as u ORDER BY u.userId";
        return (List<Users>) entityManager.createQuery(hql).getResultList();
    }

	@Override
	public Users findUserByUserId(int userId) {
		return entityManager.find(Users.class, userId);
	}


}
