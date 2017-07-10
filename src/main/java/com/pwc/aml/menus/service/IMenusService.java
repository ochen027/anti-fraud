package com.pwc.aml.menus.service;

import java.util.List;

import com.pwc.aml.menus.entity.Menus;

public interface IMenusService {
    public List<Menus> listUserMenus(int userId);

    public List<Menus> listUserChildMenus(int userId, int menuId);
}
