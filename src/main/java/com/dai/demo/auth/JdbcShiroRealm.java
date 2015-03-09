package com.dai.demo.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by wzdxt on 15/3/25.
 */
public class JdbcShiroRealm extends AuthenticatingRealm {

    // 获取认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();

        if (username != null) {
            return new SimpleAuthenticationInfo(
                    username, username, getName());
        }

        return null;
    }

}
