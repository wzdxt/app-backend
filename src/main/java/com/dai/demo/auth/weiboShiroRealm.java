package com.dai.demo.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by wzdxt on 15/3/25.
 */
public class weiboShiroRealm extends AuthorizingRealm {

    // 用于获取用户信息及用户权限信息的业务接口

    // 获取授权信息
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.fromRealm(
                getName()).iterator().next();

        return null;
    }

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
