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

    public List<Roles> getlRoles() {
        return lRoles;
    }

    public void setlRoles(List<Roles> lRoles) {
        this.lRoles = lRoles;
    }
}
