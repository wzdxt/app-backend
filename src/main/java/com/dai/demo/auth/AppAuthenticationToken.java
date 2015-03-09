package com.dai.demo.auth;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by wzdxt on 15/3/27.
 */
public class AppAuthenticationToken extends UsernamePasswordToken {
    public static final int TYPE_NATIVE = 1;
    public static final int TYPE_WEIBO = 2;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AppAuthenticationToken(String username, String password, int type) {
        this.setUsername(username);
        this.setPassword(password.toCharArray());
        this.setType(type);
    }
}
