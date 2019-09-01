package com.github.binarylei.springboot.starter.controller;

import com.github.binarylei.springboot.starter.bean.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class UserController {

    @Autowired
    private UserProperties user;

    @GetMapping("/user")
    public UserProperties getUser() {
        return user;
    }
}
