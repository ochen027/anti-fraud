package com.pwc.component.authorize.roles.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.component.authorize.roles.entity.Roles;

@Transactional
@Repository
public class RolesDAO implements IRolesDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> listAll() {
		String hql = "FROM Roles WHERE status = 1 ORDER BY id";
		return (List<Roles>)entityManager.createQuery(hql).getResultList();
	}
}
