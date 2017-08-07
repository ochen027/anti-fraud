package com.pwc.aml.menus.dao;


import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.criteria.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pwc.component.workflow.entity.Workflow;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.aml.menus.entity.Menus;


@Transactional
@Repository
public class MenusDAO implements IMenusDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<Menus> listUserRootMenus(int userId) {
        String hql = "SELECT DISTINCT M.id, M.menuName, M.menuURL, M.menuICO, M.menuDesc, M.menuParentId FROM Menus M, RoleMenu RM, Roles R, Users U, Groups G, UserGroup UG, GroupRole GR " +
                "WHERE" +
                "  U.id = UG.userId" +
                " AND G.id = UG.groupId" +
                " AND G.id = GR.groupId" +
                " AND R.id = GR.roleId" +
                " AND M.id = RM.menuId" +
                " AND R.id = RM.roleId" +
                " AND U.id = ?" +
                " AND U.status = 1" +
                " AND R.status = 1" +
                " AND M.status = 1" +
                " AND M.menuParentId=0 ORDER BY M.id";
        List<Object[]> list = entityManager.createQuery(hql).setParameter(1, userId).getResultList();
        
        return this.convertMenuData(list);
    }

    @Override
    public List<Menus> listUserChildMenus(int userId, int menuId) {
        String hql = "SELECT DISTINCT M.id, M.menuName, M.menuURL, M.menuICO, M.menuDesc, M.menuParentId FROM Menus M, RoleMenu RM, Roles R, Users U, Groups G, UserGroup UG, GroupRole GR "
        		+"WHERE U.id = UG.userId "
        		+ "AND G.id = UG.groupId "
        		+ "AND G.id = GR.groupId "
        		+ "AND R.id = GR.roleId "
        		+ "AND M.id = RM.menuId "
        		+ "AND R.id = RM.roleId "
        		+ "AND U.id = ? "
        		+ "AND U.status = 1 AND R.status = 1 AND M.status = 1 "
        		+ "AND M.menuParentId = ? ORDER BY M.id";
        return this.convertMenuData(entityManager.createQuery(hql).setParameter(1, userId).setParameter(2, menuId).getResultList());
    }

	public Menus findByMenuId(String menuId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Menus> cq = cb.createQuery(Menus.class);
		Root<Menus> rootEntry = cq.from(Menus.class);
		Predicate predicate = cb.equal(rootEntry.get("menuId"), menuId);
		CriteriaQuery<Menus> single = cq.select(rootEntry).where(predicate);
		TypedQuery<Menus> query = entityManager.createQuery(single);
		List<Menus> wfs = query.getResultList();
		if (wfs.isEmpty()) {
			return null;
		} else {
			return wfs.get(0);
		}
	}

	@Override
	public List<Menus> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Menus> cq = cb.createQuery(Menus.class);
		Root<Menus> rootEntry = cq.from(Menus.class);
		CriteriaQuery<Menus> all = cq.select(rootEntry);
		TypedQuery<Menus> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();

	}

	private List<Menus> convertMenuData(List<Object[]> list){
    	if(null != list  && list.size() > 0){
			List<Menus> mList = new ArrayList<Menus>();
    		Iterator iter = list.iterator();
    		while(iter.hasNext()){
    			Object[] obj = (Object[]) iter.next();
    			Menus m = new Menus();
    			int mId = (Integer)obj[0];
    			if(this.isDuplicateMenus(mList, mId)){
    				continue;
				}
    			m.setId(mId);
    			m.setMenuName((String)obj[1]);
    			m.setMenuURL((String)obj[2]);
    			m.setMenuICO((String)obj[3]);
    			m.setMenuDesc((String)obj[4]);
    			m.setMenuParentId(null == obj[5] ? 0 : (Integer)obj[5]);
    			mList.add(m);
    		}
    		return mList;
    	}
		return null;
    }

    private boolean isDuplicateMenus(List<Menus> mList, int menuId){
    	for(Menus m : mList){
    		if(menuId == m.getId()){
    			return true;
			}
		}
		return false;
	}
	@Override
	public Menus createMenus(Menus m) {
		entityManager.persist(m);
		return m;
	}

	@Override
	public void updateMenus(Menus m) {
		entityManager.merge(m);
		entityManager.flush();
	}

	@Override
	public void deleteMenus(Menus m) {
		m.setStatus(false);
		this.updateMenus(m);

	}

	@Override
	public Menus getMenus(int menuId) {
		return entityManager.find(Menus.class, menuId);
	}

}
