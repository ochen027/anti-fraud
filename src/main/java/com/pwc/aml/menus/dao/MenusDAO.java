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
                "FROM MENUS M, ROLEMENU RM, ROLES R, USERS U, GROUPS G, USERGROUP UG, GROUPROLE GR\n" +
                "WHERE\n" +
                "  U.USER_ID = UG.USER_ID\n" +
                "AND G.GROUP_ID = UG.GROUP_ID\n" +
                "AND G.GROUP_ID = GR.GROUP_ID\n" +
                "AND R.ROLE_ID = GR.ROLE_ID\n" +
                "AND M.MENU_ID = RM.MENU_ID\n" +
                "AND R.ROLE_ID = RM.ROLE_ID\n" +
                "AND U.USER_ID = ?\n" +
                "AND U.STATUS = 1\n" +
                "AND R.STATUS = 1\n" +
                "AND M.STATUS = 1\n" +
                "AND M.MENU_PARENTID IS NULL";
        return this.convertData(entityManager.createNativeQuery(sql).setParameter(1, userId).getResultList());
    }

    @Override
    public List<Menus> listUserChildMenus(int menuId) {
        String sql ="SELECT M.MENU_ID, M.MENU_NAME, M.MENU_URL, M.MENU_DESC,M.MENU_ICO,M.MENU_PARENTID "+
                "FROM MENUS M\n" +
                "WHERE\n" +
                " M.MENU_PARENTID = ?\n" +
                "AND M.STATUS = 1";

        return this.convertData(entityManager.createNativeQuery(sql).setParameter(1, menuId).getResultList());
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
