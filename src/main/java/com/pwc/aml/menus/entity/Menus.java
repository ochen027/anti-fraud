package com.pwc.aml.menus.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pwc.common.base.entity.BaseEntity;

@Entity
@Table(name="MENUS")
public class Menus extends BaseEntity{

	private static final long serialVersionUID = 1L;
	@Column(name="MENU_NAME")
    private String menuName;
    @Column(name="MENU_URL")
    private String menuURL;
    @Column(name="MENU_DESC")
    private String menuDesc;
    @Column(name="MENU_ICO")
    private String menuICO;
    @Column(name="MENU_PARENTID")
    private int menuParentId;
    @Transient
    private List<Menus> childList;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getMenuICO() {
        return menuICO;
    }

    public void setMenuICO(String menuICO) {
        this.menuICO = menuICO;
    }

    public int getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(int menuParentId) {
        this.menuParentId = menuParentId;
    }


    public List<Menus> getChildList() {
        return childList;
    }

    public void setChildList(List<Menus> childList) {
        this.childList = childList;
    }
}
