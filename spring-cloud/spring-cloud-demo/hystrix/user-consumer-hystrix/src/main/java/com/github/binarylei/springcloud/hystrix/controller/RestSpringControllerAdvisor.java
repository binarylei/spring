package com.github.binarylei.springcloud.hystrix.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

/**
 * Spring 自带的异常处理机制：@RestControllerAdvice @ControllerAdvice
 *
 * @author leigang
 * @version 2019-03-20
 */
@RestControllerAdvice
public class RestSpringControllerAdvisor {

    @ResponseBody
    @ExceptionHandler(value = TimeoutException.class)
    public String timeOutException() {
        return "timeout";
    }

}
