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
    private String name;
}
