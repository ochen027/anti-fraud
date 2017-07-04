package com.pwc.aml.users.dao;

import com.pwc.aml.users.entity.UserGroupRoleBean;
import com.pwc.aml.users.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String hql = "FROM Users as users WHERE users.userName = ? and users.status = 1";
        List uList = entityManager.createQuery(hql).setParameter(1, users.getUserName()).getResultList();
        Users u = null;
        if(1 == uList.size()){
            u = (Users)uList.get(0);
        }
        return u;
    }

    @Override
    public List<UserGroupRoleBean> fetchUserGroups(int userId) {
        String sql = "SELECT U.USER_ID, U.USER_NAME, G.GROUP_NAME, G.GROUP_ID " +
                "FROM USERS AS U, GROUPS AS G, USERGROUP AS UG" +
                " WHERE U.USER_ID = UG.USER_ID AND G.GROUP_ID = UG.GROUP_ID AND U.STATUS = 1 AND G.STATUS = 1 AND U.USER_ID = ?";

        return  entityManager.createNativeQuery(sql).setParameter(1, userId).getResultList();
    }

    @Override
    public List<UserGroupRoleBean> fetchGroupRoles(int groupId) {
        String sql = "SELECT G.GROUP_NAME, G.GROUP_ID, R.ROLE_ID, R.ROLE_NAME " +
                "FROM ROLES AS R, GROUPS AS G, GROUPROLE AS GR" +
                " WHERE R.ROLE_ID = GR.ROLE_ID AND G.GROUP_ID = GR.GROUP_ID AND R.STATUS = 1 AND G.STATUS = 1 AND G.GROUP_ID = ?";

        return entityManager.createNativeQuery(sql).setParameter(1, groupId).getResultList();
    }

    @Override
    public void addNewUser(Users users) {
    }

    @Override
    public void deleteUser(String userId) {
    }

    @Override
    public void updateUser(Users users) {
    }

    @Override
    public List<Users> listAllUsers() {
        return new ArrayList<Users>();
    }


}
