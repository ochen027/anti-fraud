package com.pwc.component.authorize.groups.dao;

import com.pwc.component.authorize.groups.entity.GroupRole;
import com.pwc.component.authorize.roles.entity.Roles;
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
@Transactional
@Repository
public class GroupRoleDAO implements IGroupRoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GroupRole> getAllRolesByGroup(int groupId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupRole> cq = cb.createQuery(GroupRole.class);
        Root<GroupRole> rootEntry = cq.from(GroupRole.class);

        Predicate cgroupId = cb.equal(rootEntry.get("groupId"),groupId);
        Predicate cstatus = cb.equal(rootEntry.get("status"),true);
        cq.where(cgroupId,cstatus);

        CriteriaQuery<GroupRole> all = cq.select(rootEntry);
        TypedQuery<GroupRole> query = entityManager.createQuery(all);
        List<GroupRole> groupRoles = query.getResultList();
        return groupRoles;
    }

    @Override
    public GroupRole createGroupRole(GroupRole gr) {
        entityManager.persist(gr);
        return gr;
    }

    @Override
    public GroupRole RemoveGroupRole(GroupRole gr) {

        return null;
    }

    @Override
    public void updateGroupRole(GroupRole gr){
        entityManager.merge(gr);
        entityManager.flush();
    }

    @Override
    public GroupRole getGroupRoleById(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupRole> cq = cb.createQuery(GroupRole.class);
        Root<GroupRole> rootEntry = cq.from(GroupRole.class);

        Predicate cid = cb.equal(rootEntry.get("id"),id);
        Predicate cstatus = cb.equal(rootEntry.get("status"),true);
        cq.where(cid,cstatus);

        CriteriaQuery<GroupRole> all = cq.select(rootEntry);
        TypedQuery<GroupRole> query = entityManager.createQuery(all);
        GroupRole groupRole = query.getSingleResult();
        return groupRole;
    }

    @Override
    public GroupRole getGroupRole(int groupId, int roleId) {
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<GroupRole> cq = cb.createQuery(GroupRole.class);
            Root<GroupRole> rootEntry = cq.from(GroupRole.class);

            Predicate cgroup = cb.equal(rootEntry.get("groupId"),groupId);
            Predicate crole = cb.equal(rootEntry.get("roleId"),roleId);
            Predicate cstatus = cb.equal(rootEntry.get("status"),true);
            cq.where(cgroup,crole,cstatus);

            CriteriaQuery<GroupRole> all = cq.select(rootEntry);
            TypedQuery<GroupRole> query = entityManager.createQuery(all);
            GroupRole groupRole = query.getSingleResult();
            return groupRole;
        } catch(NoResultException e) {
            return null;
        }

    }
}
