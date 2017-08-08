package com.pwc.aml.menus.dao;

import java.util.List;

import com.pwc.aml.menus.entity.Menus;


public interface IMenusDAO {


    public List<Menus> listUserRootMenus(int userId);

    public List<Menus> listUserChildMenus(int userId, int menuId);

    List<Menus> findAll();
    Menus createMenus(Menus m);
    void updateMenus(Menus m);
    void deleteMenus(Menus m);
    Menus getMenus(int menuId);

}
