package com.github.binarylei.springcloud.hystrix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class RestSpringController {

    @GetMapping("/exception")
    public String exception() throws TimeoutException {
        int sleepTime = new Random().nextInt(200);
        if (sleepTime > 100) {
            throw new TimeoutException("take time " + sleepTime + " ms");
        }
        return "sleep: " + sleepTime + " ms";
    }
}
