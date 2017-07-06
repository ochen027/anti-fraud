package com.pwc.aml.menus.service;

import com.pwc.aml.menus.dao.IMenusDAO;
import com.pwc.aml.menus.entity.Menus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenusService implements IMenusService {

    @Autowired
    private IMenusDAO menuDAO;

    @Override
    public List<Menus> listUserMenus(int userId) {
        List<Menus> rootMenus = menuDAO.listUserRootMenus(userId);
        for (Menus m : rootMenus) {
            List childMenus = this.listUserChildMenus(m.getMenuId());
            m.setChildList(childMenus);
        }
        return rootMenus;
    }

    @Override
    public List<Menus> listUserChildMenus(int menuId) {
        List<Menus> list = menuDAO.listUserChildMenus(menuId);
        for(Menus m : list){
            List<Menus> l =this.listUserChildMenus(m.getMenuId());
            m.setChildList(l);
        }
        return list;
    }


}
