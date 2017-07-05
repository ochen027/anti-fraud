package com.pwc.aml.menus.service;

import com.pwc.aml.menus.entity.Menus;

import java.util.List;

/**
 * Created by ochen027 on 7/5/2017.
 */
public interface IMenusService {
    public List<Menus> listUserMenus(int userId);

    public List<Menus> listUserChildMenus(int menuId);
}
