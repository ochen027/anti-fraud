package com.pwc.aml.users.entity;

import com.pwc.aml.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class Users extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="USER_ID")
    private int userId;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="USER_PASSWORD")
    private String userPwd;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
