package com.pwc.aml.menus.service;

import java.util.List;

import com.pwc.aml.menus.entity.Menus;

public interface IMenusService {
    public List<Menus> listUserMenus(int userId);

    public List<Menus> listUserChildMenus(int userId, int menuId);
    Menus createMenus(Menus menus);
    void updateMenus(Menus menus);
    void deleteMenus(Menus menuId);
    Menus getSingleMenus(int menuId);
    List<Menus> findAll();

}
