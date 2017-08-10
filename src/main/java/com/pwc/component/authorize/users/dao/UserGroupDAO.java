package com.pwc.component.authorize.users.dao;

import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.users.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by iwen005 on 7/31/2017.
 */
@Repository
@Transactional
public class UserGroupDAO implements IUserGroupDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserGroup> getUserGroupByUser(int userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserGroup> cq = cb.createQuery(UserGroup.class);
        Root<UserGroup> rootEntry = cq.from(UserGroup.class);

        Predicate cuserId = cb.equal(rootEntry.get("userId"),userId);
        Predicate cstatus = cb.equal(rootEntry.get("status"),true);
        cq.where(cuserId,cstatus);

        CriteriaQuery<UserGroup> all = cq.select(rootEntry);
        TypedQuery<UserGroup> query = entityManager.createQuery(all);
        List<UserGroup> userGroups = query.getResultList();
        return userGroups;
    }

    @Override
    public UserGroup createUserGroup(UserGroup ug) {
        entityManager.merge(ug);
        return ug;
    }

    @Override
    public void updateUserGroup(UserGroup ug) {
        entityManager.merge(ug);
        entityManager.flush();

    }

    @Override
    public UserGroup RemoveUserGroup(UserGroup ug) {
        return null;
    }

    @Override
    public UserGroup getUserGroupById(int id) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserGroup> cq = cb.createQuery(UserGroup.class);
        Root<UserGroup> rootEntry = cq.from(UserGroup.class);

        Predicate cid = cb.equal(rootEntry.get("id"),id);
        Predicate cstatus = cb.equal(rootEntry.get("status"),true);
        cq.where(cid,cstatus);

        CriteriaQuery<UserGroup> all = cq.select(rootEntry);
        TypedQuery<UserGroup> query = entityManager.createQuery(all);
        UserGroup userGroup = query.getSingleResult();
        return userGroup;
    }

    @Override
    public UserGroup getUserGroup(int userId, int groupId) {
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserGroup> cq = cb.createQuery(UserGroup.class);
            Root<UserGroup> rootEntry = cq.from(UserGroup.class);

            Predicate cuserId = cb.equal(rootEntry.get("userId"),userId);
            Predicate cgroupId = cb.equal(rootEntry.get("groupId"),groupId);
            Predicate cstatus = cb.equal(rootEntry.get("status"),true);
            cq.where(cuserId,cgroupId,cstatus);

            CriteriaQuery<UserGroup> all = cq.select(rootEntry);
            TypedQuery<UserGroup> query = entityManager.createQuery(all);
            UserGroup userGroup =query.getSingleResult();
            return userGroup;
        }catch(NoResultException nre){
            return null;
        }
    }
}
