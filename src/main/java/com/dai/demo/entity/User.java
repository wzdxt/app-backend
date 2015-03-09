package com.dai.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by wzdxt on 15/3/22.
 */
@Data
@Component
public class User {
    private long id;
    private String name;
}
