package com.github.framework.test.dubbo.api.domain;

import java.io.Serializable;

/**
 * @author longhairen
 * @create 2018-02-23 21:32
 * @description
 **/
public class UserDTO implements Serializable{

    private static final long serialVersionUID = -4032727578231208253L;
    private String userName;
    private String password;

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
