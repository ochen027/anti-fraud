package com.pwc.aml.roles.entity;

import com.pwc.aml.entity.BaseEntity;
import com.pwc.aml.groups.entity.Groups;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by ochen027 on 7/4/2017.
 */
@Entity
@Table(name="ROLES")
public class Roles extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ROLE_ID")
    private int roleId;
    @Column(name="ROLE_NAME")
    private String roleName;

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="GROUPROLE",
            joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "groupId", referencedColumnName = "GROUP_ID"))
    private List<Groups> lGroup;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
