package com.github.binarylei.springboot.web.controller;

import com.github.binarylei.springboot.web.bean.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author: leigang
 * @version: 2018-09-01
 */
@RestController
public class HelloController {

    @RequestMapping("/v1/test")
    public String test1() {
        return "test";
    }

    @RequestMapping("/v1/{city_id}/{user_id}")
    public String test2(@PathVariable("city_id") String cityId, @PathVariable(value = "user_id") String userId) {
        return cityId;
    }

    @GetMapping("/v1/{user_id}")
    public String test3(@PathVariable("user_id") String userId) {
        return userId;
    }

    @GetMapping("/v1/test4")
    public String test4(@RequestParam(name = "user_id", defaultValue = "2") String userId) {
        return userId;
    }

    @PostMapping("/v1/test5")
    public String test5(@Valid @RequestBody User user) {
        return user.getUsername();
    }

    @GetMapping("/v1/get_header")
    public String test6(@RequestHeader("access_token") String token) {
        return token;
    }

    @GetMapping("/v1/get_request")
    public String test7(HttpServletRequest request) {
        return request.getParameter("username");
    }

    @GetMapping("/v1/entity")
    public ResponseEntity<String> test8() {
        return ResponseEntity.ok("hello, world!");
    }

}
