package com.dai.demo.webapi.auth;

import lombok.NonNull;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.security.auth.login.FailedLoginException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Component
@Path("/auth")
public class Auth {
    private static final Logger log = LoggerFactory.getLogger(Auth.class);

	@GET @Path("/{action : login|logout}")
	@Produces(MediaType.TEXT_PLAIN)
	public String auth(@NonNull @PathParam("action") String action) throws FailedLoginException {
        Subject currentUser = SecurityUtils.getSubject();
        if (action.equals("login")) {
            if (currentUser.isAuthenticated()) {
                return "succeed";
            } else {
                log.error("Unexpected system error. Login succeed but cannot get user info.");
                throw new FailedLoginException();
            }
        } else {
            if (currentUser.isAuthenticated()) {
                Object obj = currentUser.getPrincipal();
                log.info("{} logged out.", obj.toString());
                currentUser.logout();
            }
            return "succeed";
        }
	}
}
