package com.pwc.aml.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pwc.aml.base.entity.BaseEntity;

@Entity
@Table(name="USERS")
public class Users extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="USER_PASSWORD")
    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

}
