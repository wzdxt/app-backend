package com.dai.demo.webapi.auth;

import com.dai.demo.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashingPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.FailedLoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Component
@Path("/auth")
public class Auth {
    private static final Logger log = LoggerFactory.getLogger(Auth.class);

    @Autowired
    private DefaultPasswordService passwordService;

	@GET @Path("/{action : login|logout|signup}")
	@Produces(MediaType.TEXT_PLAIN)
	public String auth(@NonNull @PathParam("action") String action, @QueryParam("data") String dataStr) throws FailedLoginException {
        Subject currentUser = SecurityUtils.getSubject();
        if (action.equals("login")) {
            if (currentUser.isAuthenticated()) {
                return "succeed";
            } else {
                log.error("Unexpected system error. Login succeed but cannot get user info.");
                throw new FailedLoginException();
            }
        } else if (action.equals("logout")) {
            if (currentUser.isAuthenticated()) {
                Object obj = currentUser.getPrincipal();
                log.info("{} logged out.", obj.toString());
                currentUser.logout();
            }
            return "succeed";
        } else if (action.equals("signup")) {
            Gson gson = new GsonBuilder().create();
            Map<String, String> data = gson.fromJson(dataStr, Map.class);
            String username = data.get("username");
            String passwd = data.get("password");

            // 500000 should be randomized
            int hashIterations = 500000;
            String hashAlgorithmName = "SHA-256";
            ((DefaultHashService)passwordService.getHashService()).setHashIterations(hashIterations);
            ((DefaultHashService)passwordService.getHashService()).setHashAlgorithmName(hashAlgorithmName);
            passwd = passwordService.encryptPassword(passwd);

            User user = new User(username, passwd, hashIterations, hashAlgorithmName);
            //userDao.save(user);
            log.info("Somebody registered. {}", user.toString());

            return "succeed";
        }
        return "";
	}
}
