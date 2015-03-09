package com.dai.demo.webapi;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by wzdxt on 15/3/25.
 */
@Component
@Path("auth")
public class UserResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@PathParam("username") String username, @PathParam("passwd") String passwd) {
        return "not implemented";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String logout() {
        return "not implemented";
    }
}
