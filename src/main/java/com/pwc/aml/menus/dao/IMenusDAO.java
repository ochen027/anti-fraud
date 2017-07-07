package com.pwc.aml.menus.dao;

import com.pwc.aml.menus.entity.Menus;

import java.util.List;


public interface IMenusDAO {

    public List<Menus> listUserRootMenus(int userId);

    public List<Menus> listUserChildMenus(int userId, int menuId);
}
