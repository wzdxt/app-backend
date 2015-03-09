package com.dai.demo.auth.realm;

import com.dai.demo.auth.token.AppAuthenticationToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wzdxt on 15/3/25.
 * Native account login realm
 */
public class NativeShiroRealm extends AuthenticatingRealm {
    private static final Logger log = LoggerFactory.getLogger(JdbcShiroRealm.class);
    @Autowired
    private CredentialsMatcher allowAllCredentialsMatcher;

    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        String token = ((AppAuthenticationToken) authcToken).getToken();

        /*
        get user info by token from weibo
        and then fetch it in db
        ... ...
        return AuthenticationInfo
         */

        return null;
    }

    @Override
    public boolean supports(AuthenticationToken authcToken) {
        return authcToken instanceof AppAuthenticationToken && ((AppAuthenticationToken) authcToken).getType() == AppAuthenticationToken.TYPE_NATIVE;
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        return allowAllCredentialsMatcher;
    }
}
