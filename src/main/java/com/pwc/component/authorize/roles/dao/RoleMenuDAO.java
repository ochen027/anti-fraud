package com.pwc.component.authorize.roles.dao;

import com.pwc.component.authorize.roles.entity.RoleMenu;
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

@Repository
@Transactional
public class RoleMenuDAO implements IRoleMenuDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RoleMenu> getRoleMenuByRole(int roleId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoleMenu> cq = cb.createQuery(RoleMenu.class);
        Root<RoleMenu> rootEntry = cq.from(RoleMenu.class);

        Predicate croleId = cb.equal(rootEntry.get("roleId"),roleId);
        Predicate cstatus = cb.equal(rootEntry.get("status"),true);
        cq.where(croleId,cstatus);

        CriteriaQuery<RoleMenu> all = cq.select(rootEntry);
        TypedQuery<RoleMenu> query = entityManager.createQuery(all);
        List<RoleMenu> roleMenus = query.getResultList();
        return roleMenus;
    }

    @Override
    public RoleMenu createRoleMenu(RoleMenu rm) {
        entityManager.merge(rm);
        return rm;
    }

    @Override
    public void updateRoleMenu(RoleMenu rm) {
        entityManager.merge(rm);
        entityManager.flush();

    }

    @Override
    public RoleMenu RemoveRoleMenu(RoleMenu rm) {
        return null;
    }

    @Override
    public RoleMenu getRoleMenuById(int id) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoleMenu> cq = cb.createQuery(RoleMenu.class);
        Root<RoleMenu> rootEntry = cq.from(RoleMenu.class);

        Predicate cid = cb.equal(rootEntry.get("id"),id);
        Predicate cstatus = cb.equal(rootEntry.get("status"),true);
        cq.where(cid,cstatus);

        CriteriaQuery<RoleMenu> all = cq.select(rootEntry);
        TypedQuery<RoleMenu> query = entityManager.createQuery(all);
        RoleMenu roleMenu = query.getSingleResult();
        return roleMenu;
    }

    @Override
    public RoleMenu getRoleMenu(int roleId, int menuId) {
        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<RoleMenu> cq = cb.createQuery(RoleMenu.class);
            Root<RoleMenu> rootEntry = cq.from(RoleMenu.class);

            Predicate croleId = cb.equal(rootEntry.get("roleId"),roleId);
            Predicate cmenuId = cb.equal(rootEntry.get("menuId"),menuId);
            Predicate cstatus = cb.equal(rootEntry.get("status"),true);
            cq.where(croleId,cmenuId,cstatus);

            CriteriaQuery<RoleMenu> all = cq.select(rootEntry);
            TypedQuery<RoleMenu> query = entityManager.createQuery(all);
            RoleMenu roleMenu =query.getSingleResult();
            return roleMenu;
        }catch(NoResultException nre){
            return null;
        }
    }
    public List<RoleMenu> getRoleMenu(){

        try{
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<RoleMenu> cq = cb.createQuery(RoleMenu.class);
            Root<RoleMenu> rootEntry = cq.from(RoleMenu.class);

            Predicate cstatus = cb.equal(rootEntry.get("status"),true);
            cq.where(cstatus);

            CriteriaQuery<RoleMenu> all = cq.select(rootEntry);
            TypedQuery<RoleMenu> query = entityManager.createQuery(all);
            List<RoleMenu> roleMenus = query.getResultList();
            return roleMenus;
        }catch(NoResultException nre){
            return null;
        }
    }
}
