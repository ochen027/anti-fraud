package com.pwc.aml.menus.service;

import java.util.List;

import com.pwc.aml.menus.dao.MenusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.aml.menus.dao.IMenusDAO;
import com.pwc.aml.menus.entity.Menus;

@Service
public class MenusService implements IMenusService {

    @Autowired
    private IMenusDAO menuDAO;
    @Override
    public List<Menus> listUserMenus(int userId) {
        List<Menus> rootMenus = menuDAO.listUserRootMenus(userId);
        if(null == rootMenus){
            return null;
        }
        for (Menus m : rootMenus) {
            List<Menus> childMenus = this.listUserChildMenus(userId, m.getId());
            if(null == childMenus){
                continue;
            }
            m.setChildList(childMenus);
        }
        return rootMenus;
    }

    @Override
    public List<Menus> listUserChildMenus(int userId, int menuId) {
        List<Menus> list = menuDAO.listUserChildMenus(userId, menuId);
        if(null != list && list.size() > 0){
	        for(Menus m : list){
                List<Menus> l =this.listUserChildMenus(userId, m.getId());
	            m.setChildList(l);
	        }
        }
        return list;
    }
    @Override
    public Menus createMenus(Menus m) {
        menuDAO.createMenus(m);
        return m;
    }

    @Override
    public void updateMenus(Menus m) {
        menuDAO.updateMenus(m);
    }

    @Override
    public void deleteMenus(Menus menus) {

        menuDAO.deleteMenus(menus);

    }

    @Override
    public Menus getSingleMenus(int menuId) {
        return menuDAO.getMenus(menuId);
    }

    @Override
    public List<Menus> findAll() {
        return menuDAO.findAll();
    }

}
