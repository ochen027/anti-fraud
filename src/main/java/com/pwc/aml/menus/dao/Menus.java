package com.pwc.aml.menus.dao;

import com.pwc.aml.entity.BaseEntity;
import com.pwc.aml.roles.entity.Roles;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */
@Entity
@Table(name="MENUS")
public class Menus extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="MENU_ID")
    private int menuId;
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


    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="ROLEMENU",
            joinColumns = @JoinColumn(name = "menuId", referencedColumnName = "MENU_ID"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "ROLE_ID"))
    private List<Roles> lRoles;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

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

    public List<Roles> getlRoles() {
        return lRoles;
    }

    public void setlRoles(List<Roles> lRoles) {
        this.lRoles = lRoles;
    }
}
