package com.pwc.aml.menus.service;

import com.pwc.aml.menus.entity.Menus;

import java.util.List;

public interface IMenusService {
    public List<Menus> listUserMenus(int userId);

    public List<Menus> listUserChildMenus(int userId, int menuId);
}
