package com.pwc.component.authorize.roles.dao;

import com.pwc.aml.menus.entity.Menus;
import com.pwc.component.authorize.roles.entity.RoleMenu;
import com.pwc.component.authorize.roles.entity.Roles;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
@Transactional
@Repository
public class RolesDAO implements IRolesDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Roles checkRoleName(Roles roles) {
		String hql = "FROM Roles WHERE roleName = ? and status = 1";
		List<Roles> rList = (List<Roles>) entityManager.createQuery(hql).setParameter(1, roles.getRoleName()).getResultList();
		entityManager.clear();
		Roles r = null;
		if (1 == rList.size()) {
			r = rList.get(0);
		}
		return r;
	}

	/*@Override
	public List<Roles> fetchRoleMenu(int roleId) {
		String hql = "SELECT R.id, R.roleMenu FROM Roles AS R, Menus AS M, RoleMenu AS RM" +
				" WHERE R.id = RM.roleId AND M.id = RM.menuId AND R.status = 1 AND M.status = 1 AND R.id = ?";
		List rlist = entityManager.createQuery(hql).setParameter(1, roleId).getResultList();
		Iterator iter = rlist.iterator();
		List<Menus> menusList = new ArrayList<Menus>();
		while(iter.hasNext()){
			Object[] obj = (Object[]) iter.next();
			Menus m = new Menus();
			m.setId(null == obj[0] ? 0 : (Integer)obj[0]);
			m.setMenuName((String)obj[1]);
			menusList.add(m);
		}

		return menusList;
	}*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);
		Root<Roles> rootEntry = cq.from(Roles.class);

		Predicate condition = cb.equal(rootEntry.get("status"), true);
		cq.where(condition);

		CriteriaQuery<Roles> all = cq.select(rootEntry);
		TypedQuery<Roles> query = entityManager.createQuery(all);
		List<Roles> roles = query.getResultList();
		return roles;
	}

	@Override
	public Roles createRoles(Roles ur) {
		entityManager.merge(ur);
		return ur;
	}

	@Override
	public Roles updateRoles(Roles ur) {
		//Roles r = this.getRoles(ur.getId());
		entityManager.merge(ur);
		entityManager.flush();
		return ur;
	}

	@Override
	public void deleteRoles(Roles ur) {
		ur.setStatus(false);
		this.updateRoles(ur);

	}

	@Override
	public Roles getRoles(int roleId) {
		return entityManager.find(Roles.class, roleId);
	}


	@Override
	public void addRoleIntoMenu(RoleMenu rm, String roleName) {
		rm.setCreatedBy(roleName);
		rm.setLastUpdatedBy(roleName);
		rm.setCreationDate(new Date());
		entityManager.persist(rm);
	}

	@Override
	public void updateRoleMenu(RoleMenu rm, String roleName) {
		RoleMenu rMenu = this.getRoleMenuRelationship(rm.getId());
		rMenu.setLastUpdatedBy(roleName);
		rMenu.setMenuId(rm.getMenuId());
		rMenu.setRoleId(rm.getRoleId());
		entityManager.flush();
	}

	@Override
	public void deleteMenuFromRole(int id, String roleName) {
		RoleMenu rMenu = this.getRoleMenuRelationship(id);
		rMenu.setLastUpdatedBy(roleName);
		rMenu.setStatus(false);
		entityManager.flush();
	}

	@Override
	public RoleMenu getRoleMenuRelationship(int id) {

		return entityManager.find(RoleMenu.class, id);
	}
}
/*	@Override
	public List<Roles> searchRolesByConditions(int roleId, String roleName) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);
		Root<Roles> rootEntry = cq.from(Roles.class);

		List<Predicate> predicatesList = new ArrayList<Predicate>();
		predicatesList.add(cb.equal(rootEntry.get("status"),true));
		if(roleId != 0){
			predicatesList.add(cb.and(cb.equal(rootEntry.get("id"),roleId)));
		}
		if(roleName != null && !roleName.equalsIgnoreCase("undefined") && !roleName.equals("")){
			predicatesList.add(cb.and(cb.equal(rootEntry.get("roleName"),roleName)));
		}
		cq.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

		CriteriaQuery<Roles> all = cq.select(rootEntry);
		TypedQuery<Roles> query = entityManager.createQuery(all);
		List<Roles> roles = query.getResultList();
		return roles;
	}

}*/
