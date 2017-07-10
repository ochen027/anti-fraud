package com.pwc.aml.menus.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        String hql = "SELECT M.id, M.menuName, M.menuURL, M.menuICO, M.menuDesc, M.menuParentId FROM Menus M, RoleMenu RM, Roles R, Users U, Groups G, UserGroup UG, GroupRole GR " +
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
                " AND M.menuParentId IS NULL ORDER BY M.id";
        List<Object[]> list = entityManager.createQuery(hql).setParameter(1, userId).getResultList();
        
        return this.convertMenuData(list);
    }

    @Override
    public List<Menus> listUserChildMenus(int userId, int menuId) {
        String hql = "SELECT M.id, M.menuName, M.menuURL, M.menuICO, M.menuDesc, M.menuParentId FROM Menus M, RoleMenu RM, Roles R, Users U, Groups G, UserGroup UG, GroupRole GR "
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
    
    private List<Menus> convertMenuData(List<Object[]> list){
    	if(null != list  && list.size() > 0){
    		Iterator iter = list.iterator();
    		List<Menus> mList = new ArrayList<Menus>();
    		while(iter.hasNext()){
    			Object[] obj = (Object[]) iter.next();
    			Menus m = new Menus();
    			m.setId((Integer)obj[0]);
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

}
