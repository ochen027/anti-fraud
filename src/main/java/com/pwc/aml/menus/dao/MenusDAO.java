package com.pwc.aml.menus.dao;


import com.pwc.aml.menus.entity.Menus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ochen027 on 7/5/2017.
 */
@Transactional
@Repository
public class MenusDAO implements IMenusDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List listUserRootMenus(int userId) {
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
        return entityManager.createNativeQuery(sql).setParameter(1, userId).getResultList();
    }

    @Override
    public List listUserChildMenus(int menuId) {
        String sql ="SELECT M.MENU_ID, M.MENU_NAME, M.MENU_URL, M.MENU_DESC,M.MENU_ICO,M.MENU_PARENTID "+
                "FROM MENUS M\n" +
                "WHERE\n" +
                " M.MENU_PARENTID = ?\n" +
                "AND M.STATUS = 1";
        return (List<Menus>)entityManager.createNativeQuery(sql).setParameter(1, menuId).getResultList();
    }
}
