package com.github.binarylei.springboot.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

//@ControllerAdvice
@RestControllerAdvice
public class ErrorPageController2 {

    @ExceptionHandler(NullPointerException.class)
    public Object pageNotFound() {
        return Collections.singletonMap("error", "NullPointerException");
    }

}
