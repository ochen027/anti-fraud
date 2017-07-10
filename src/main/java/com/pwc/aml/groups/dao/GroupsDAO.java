package com.pwc.aml.groups.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.groups.entity.Groups;


@Transactional
@Repository
public class GroupsDAO implements IGroupsDAO {
	
    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Groups> listAllGroups() {
		String hql = "FROM Groups WHERE status = 1 ORDER BY id";
		return (List<Groups>)entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void createGroups(Groups ug) {
		entityManager.persist(ug);
	}

	@Override
	public void updateGroups(Groups ug) {
		Groups g = this.getGroups(ug.getId());
		g.setGroupName(ug.getGroupName());
		entityManager.flush();
	}

	@Override
	public Groups getGroups(int groupId) {
		return entityManager.find(Groups.class, groupId);
	}

}
