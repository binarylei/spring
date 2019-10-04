package binarylei.sc.config.controller;

import binarylei.sc.config.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leigang
 * @version 2019-03-23
 */
@RestController
@EnableConfigurationProperties(User.class)
public class UserController {

    @Autowired
    private User user;

    @GetMapping("/user")
    public User user() {
        return user;
    }
}
