package com.dai.demo.auth.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by wzdxt on 15/3/27.
 */
public class AppAuthenticationToken extends UsernamePasswordToken implements SsoAuthenticationToken {
    public static final int TYPE_NATIVE = 1;
    public static final int TYPE_WEIBO = 2;

    private int type;
    private String token;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AppAuthenticationToken(String username, String password) {
        this.setUsername(username);
        this.setPassword(password.toCharArray());
        this.setType(TYPE_NATIVE);
    }

    public AppAuthenticationToken(String token, int type) {
        this.setToken(token);
        this.setType(type);
    }

    @Override
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
