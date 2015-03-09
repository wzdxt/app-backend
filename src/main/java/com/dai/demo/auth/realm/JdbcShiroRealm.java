package com.dai.demo.auth.realm;

import com.dai.demo.auth.token.AppAuthenticationToken;
import com.dai.demo.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wzdxt on 15/3/25.
 * Native account login realm
 */
public class JdbcShiroRealm extends AuthenticatingRealm {
    private static final Logger log = LoggerFactory.getLogger(JdbcShiroRealm.class);
    @Autowired private CredentialsMatcher passwordMatcher;
    @Autowired private DefaultHashService hashService;

    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        if (username != null) {
            //get user info from database by username
            String password = "$shiro1$SHA-256$500000$Q28qvfVeVo70Le2Qm6QyBw==$Si0m9fV3i5r2nYbkOMSAxjM/9v4dHl1b0SYb7DC1wos=";
            password = "$shiro1$SHA-256$500000$UfLW15tRC6KvloY0qQb2YA==$58pWohMVbBCzCulnnMle17opqlsq5DWnxZzZ9U5nC7Y=";
            //passowrd:123
            int hashIterations = 500000;
            String hashAlgorithmName = "SHA-256";
            User user = new User(username, password, hashIterations, hashAlgorithmName);

            hashService.setHashIterations(user.getHashIterations());
            hashService.setHashAlgorithmName(user.getHashAlgorithmName());

            return new SimpleAuthenticationInfo(
                    user, password, getName());
        }

        return null;
    }

    @Override
    public boolean supports(AuthenticationToken authcToken) {
        return authcToken instanceof AppAuthenticationToken && ((AppAuthenticationToken) authcToken).getType() == AppAuthenticationToken.TYPE_NATIVE;
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        return passwordMatcher;
    }
}
