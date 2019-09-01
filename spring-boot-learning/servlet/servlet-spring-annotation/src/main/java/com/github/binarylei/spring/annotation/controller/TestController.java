package com.github.binarylei.spring.annotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leigang
 * @version: 2018-10-19
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/user/{username:[a-b]+}")
    public String get(@PathVariable("username") String username) {
        return username;
    }

    @PostMapping(value = "/user/{username:[a-b]+}")
    public String post(@PathVariable("username") String username) {
        return username;
    }

    @GetMapping(value = "/user/{username:[c-d]+}")
    public String getcd(@PathVariable("username") String username) {
        return username;
    }
}
