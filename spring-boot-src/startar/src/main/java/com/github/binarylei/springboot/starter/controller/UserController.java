package com.github.binarylei.springboot.starter.controller;

import com.github.binarylei.springboot.starter.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private User user;

    @GetMapping("/user")
    public User getUser() {
        return user;
    }
}
