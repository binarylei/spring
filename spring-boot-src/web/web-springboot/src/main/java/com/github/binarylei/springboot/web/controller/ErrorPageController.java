package com.github.binarylei.springboot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {

    @GetMapping("/404")
    public Object pageNotFound() {
        return "自定义 404";
    }

    @GetMapping("/null")
    public Object nullError() {
        String str = null;
        str.indexOf('s');
        return "null";
    }
}
