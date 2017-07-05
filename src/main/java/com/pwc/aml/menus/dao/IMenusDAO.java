package com.pwc.aml.menus.dao;

import com.pwc.aml.menus.entity.Menus;

import java.util.List;

/**
 * Created by ochen027 on 7/5/2017.
 */
public interface IMenusDAO {

    public List listUserRootMenus(int userId);

    public List listUserChildMenus(int menuId);
}
