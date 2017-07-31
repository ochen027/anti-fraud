package com.pwc.component.authorize.roles.dao;

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
import java.util.List;

@Transactional
@Repository
public class RolesDAO implements IRolesDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);
		Root<Roles> rootEntry = cq.from(Roles.class);

		Predicate condition = cb.equal(rootEntry.get("status"),true);
		cq.where(condition);

		CriteriaQuery<Roles> all = cq.select(rootEntry);
		TypedQuery<Roles> query = entityManager.createQuery(all);
		List<Roles> roles = query.getResultList();
		return roles;
	}

	@Override
	public Roles createRoles(Roles ur) {
		entityManager.persist(ur);
		return ur;
	}

	@Override
	public void updateRoles(Roles ur) {
		//Roles r = this.getRoles(ur.getId());
		entityManager.merge(ur);
		entityManager.flush();
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

}
