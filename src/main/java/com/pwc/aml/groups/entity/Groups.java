package com.pwc.aml.groups.entity;

import com.pwc.aml.entity.BaseEntity;
import com.pwc.aml.users.entity.Users;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by ochen027 on 7/3/2017.
 */
@Entity
@Table(name="GROUPS")
public class Groups extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="GROUP_ID")
    private int userGroupId;
    @Column(name="GROUP_NAME")
    private String userGroupName;


    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="USERGROUP",
            joinColumns = @JoinColumn(name = "groupId", referencedColumnName = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "USER_ID"))
    private List<Users> lUsers;

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public List<Users> getlUsers() {
        return lUsers;
    }

    public void setlUsers(List<Users> lUsers) {
        this.lUsers = lUsers;
    }
}
