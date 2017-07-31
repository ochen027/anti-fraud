package com.pwc.component.authorize.groups.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pwc.component.authorize.groups.entity.Groups;
import com.pwc.component.authorize.roles.entity.Roles;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class GroupsDAO implements IGroupsDAO {
	
    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Groups> listAllGroups() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Groups> cq = cb.createQuery(Groups.class);
		Root<Groups> rootEntry = cq.from(Groups.class);

		Predicate condition = cb.equal(rootEntry.get("status"),true);
		cq.where(condition);

		CriteriaQuery<Groups> all = cq.select(rootEntry);
		TypedQuery<Groups> query = entityManager.createQuery(all);
		List<Groups> groups = query.getResultList();
		return groups;
	}

	@Override
	public Groups createGroups(Groups ug) {
		entityManager.persist(ug);
		return ug;
	}

	@Override
	public Groups updateGroups(Groups ug) {
		//Groups g = this.getGroups(ug.getId());
		//g.setGroupName(ug.getGroupName());
		entityManager.merge(ug);
		entityManager.flush();
		return ug;
	}

	@Override
	public Groups getGroups(int groupId) {
		return entityManager.find(Groups.class, groupId);
	}

}
