package com.pwc.aml.menus.service;

import com.pwc.aml.menus.dao.IMenusDAO;
import com.pwc.aml.menus.entity.Menus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ochen027 on 7/5/2017.
 */
@Service
public class MenusService implements IMenusService {

    @Autowired
    private IMenusDAO menuDAO;

    @Override
    public List<Menus> listUserMenus(int userId) {
        List rootMenus = menuDAO.listUserRootMenus(userId);
        List<Menus> rMenus = this.convertMenuData(rootMenus);
        for (Menus m : rMenus) {
            List childMenus = menuDAO.listUserChildMenus(m.getMenuId());
            List<Menus> cList = this.convertMenuData(childMenus);
            m.setChildList(cList);
        }
        return rMenus;
    }

    @Override
    public List<Menus> listUserChildMenus(int menuId) {
        return null;
    }

    private List<Menus> convertMenuData(List l){
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
