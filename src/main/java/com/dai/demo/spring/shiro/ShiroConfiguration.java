package com.dai.demo.spring.shiro;

import com.dai.demo.auth.filter.AppAuthorizationFilter;
import com.dai.demo.auth.filter.NativeLoginAuthorizationFilter;
import com.dai.demo.auth.realm.JdbcShiroRealm;
import com.dai.demo.auth.realm.NativeShiroRealm;
import com.dai.demo.auth.realm.WeiboShiroRealm;
import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Maps;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wzdxt on 15/3/27.
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public DefaultHashService hashService() {
        DefaultHashService bean = new DefaultHashService();
        bean.setHashIterations(200000);
        bean.setHashAlgorithmName("SHA-256");
        bean.setGeneratePublicSalt(true);
        bean.setPrivateSalt(ByteSource.Util.bytes("myVERYSECRETBase64EncodedSalt"));
        return bean;
    }

    @Bean
    public PasswordService passwordService() {
        DefaultPasswordService bean = new DefaultPasswordService();
        bean.setHashService(hashService());
        return bean;
    }

    @Bean
    public PasswordMatcher passwordMatcher() {
        PasswordMatcher bean = new PasswordMatcher();
        bean.setPasswordService(passwordService());
        return bean;
    }

    @Bean
    public AllowAllCredentialsMatcher allowAllCredentialsMatcher() {
        return new AllowAllCredentialsMatcher();
    }

    @Bean
    public AppAuthorizationFilter appAuthorizationFilter() {
        return new AppAuthorizationFilter();
    }

    @Bean
    public NativeLoginAuthorizationFilter nativeLoginAuthorizationFilter() {
        return new NativeLoginAuthorizationFilter();
    }

    @Bean
    public EhCacheManager shiroCacheManager() {
        return new EhCacheManager();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public JdbcShiroRealm jdbcShiroRealm() {
        JdbcShiroRealm bean = new JdbcShiroRealm();
        bean.setCacheManager(shiroCacheManager());
        return bean;
    }

    @Bean
    public NativeShiroRealm nativeShiroRealm() {
        NativeShiroRealm bean = new NativeShiroRealm();
        bean.setCacheManager(shiroCacheManager());
        return bean;
    }

    @Bean
    public WeiboShiroRealm weiboShiroRealm() {
        WeiboShiroRealm bean = new WeiboShiroRealm();
        bean.setCacheManager(shiroCacheManager());
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
        List<Realm> list = Lists.newArrayList(new Realm[]{jdbcShiroRealm(), nativeShiroRealm(), weiboShiroRealm()});
        bean.setRealms(list);
        return bean;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/webapi/auth/login");
        bean.setFilters(new HashMap<String, Filter>());
        bean.getFilters().put("nativeAuthc", nativeLoginAuthorizationFilter());
        bean.getFilters().put("authc", appAuthorizationFilter());
        bean.setFilterChainDefinitions(//"classpath:shiro.ini");
                "/webapi/*=anon\n" +
                        "/webapi/auth/logout=anon\n" +
                        "/webapi/auth/signup=anon\n" +
                        "/webapi/auth/login=nativeAuthc\n" +
                        "/webapi/auth/**=authc\n");
        return bean;
    }
}
