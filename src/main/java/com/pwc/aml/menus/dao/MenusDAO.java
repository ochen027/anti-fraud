package com.pwc.aml.menus.dao;


import com.pwc.aml.menus.entity.Menus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Repository
public class MenusDAO implements IMenusDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Menus> listUserRootMenus(int userId) {
        String sql = "SELECT M.MENU_ID, M.MENU_NAME, M.MENU_URL, M.MENU_DESC,M.MENU_ICO,M.MENU_PARENTID "+
                "FROM MENUS M, ROLEMENU RM, ROLES R, USERS U, GROUPS G, USERGROUP UG, GROUPROLE GR " +
                "WHERE" +
                "  U.USER_ID = UG.USER_ID" +
                " AND G.GROUP_ID = UG.GROUP_ID" +
                " AND G.GROUP_ID = GR.GROUP_ID" +
                " AND R.ROLE_ID = GR.ROLE_ID" +
                " AND M.MENU_ID = RM.MENU_ID" +
                " AND R.ROLE_ID = RM.ROLE_ID" +
                " AND U.USER_ID = ?" +
                " AND U.STATUS = 1" +
                " AND R.STATUS = 1" +
                " AND M.STATUS = 1" +
                " AND M.MENU_PARENTID IS NULL ORDER BY M.MENU_ID";
        return this.convertData(entityManager.createNativeQuery(sql).setParameter(1, userId).getResultList());
    }

    @Override
    public List<Menus> listUserChildMenus(int userId, int menuId) {
        String sql = "SELECT M.MENU_ID, M.MENU_NAME, M.MENU_URL, M.MENU_DESC,M.MENU_ICO,M.MENU_PARENTID FROM MENUS M, ROLEMENU RM, ROLES R, USERS U, GROUPS G, USERGROUP UG, GROUPROLE GR WHERE U.USER_ID = UG.USER_ID AND G.GROUP_ID = UG.GROUP_ID AND G.GROUP_ID = GR.GROUP_ID AND R.ROLE_ID = GR.ROLE_ID AND M.MENU_ID = RM.MENU_ID AND R.ROLE_ID = RM.ROLE_ID AND U.USER_ID = ? AND U.STATUS = 1 AND R.STATUS = 1 AND M.STATUS = 1 AND M.MENU_PARENTID = ? ORDER BY M.MENU_ID";
        return this.convertData(entityManager.createNativeQuery(sql).setParameter(1, userId).setParameter(2, menuId).getResultList());
    }

    private List<Menus> convertData(List l){
        List<Menus> list = new ArrayList<Menus>();
        for(Object o : l){
            Object[] obj = (Object[]) o;
            Menus menus = new Menus();
            menus.setMenuId((Integer) obj[0]);
            menus.setMenuName((String) obj[1]);
            menus.setMenuURL((String) obj[2]);
            menus.setMenuDesc((String) obj[3]);
            menus.setMenuICO((String) obj[4]);
            menus.setMenuParentId(obj[5] == null ? 0 : (Integer) obj[5]);
            list.add(menus);
        }
        return list;
    }
}
