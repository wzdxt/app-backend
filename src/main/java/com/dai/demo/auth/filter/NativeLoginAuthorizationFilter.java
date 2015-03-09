package com.dai.demo.auth.filter;

import com.dai.demo.auth.token.AppAuthenticationToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wzdxt on 15/3/22.
 */
public class NativeLoginAuthorizationFilter extends AuthenticatingFilter {
    private static Logger log = LoggerFactory.getLogger(NativeLoginAuthorizationFilter.class);


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            log.trace("Login submission detected.  Attempting to execute login.");
            executeLogin(request, response);
            return executeLogin(request, response);
        } else {
            String ipAddress = request.getRemoteAddr();
            log.info("Unthencated user try to accessed protected resource. IP {}", ipAddress);
            WebUtils.toHttp(response).setStatus(403);
            return false;
        }
    }

    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
        return (request instanceof HttpServletRequest) && WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String dataStr = WebUtils.getCleanParam(request, "data");
        Gson gson = new GsonBuilder().create();
        Map<String, String> data = gson.fromJson(dataStr, Map.class);

        return new AppAuthenticationToken(data.get("username"), data.get("password"));
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        WebUtils.toHttp(response).setStatus(403);
        return false;
    }
}
