package com.dai.demo.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by wzdxt on 15/3/22.
 */
@Data
@Component
@Scope("session")
public class User {
    private long id;
    private String username;
    private String password;
    private int hashIterations;
    private String hashAlgorithmName;

    public User(String username, String password, int hashIterations, String hashAlgorithmName) {
        this.username = username;
        this.password = password;
        this.hashIterations = hashIterations;
        this.hashAlgorithmName = hashAlgorithmName;
    }
}
