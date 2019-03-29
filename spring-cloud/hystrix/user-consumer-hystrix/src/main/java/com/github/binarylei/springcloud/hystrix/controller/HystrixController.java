package com.github.binarylei.springcloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class HystrixController {

    @GetMapping("hystrix")
    @HystrixCommand(
            commandProperties = {@HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            fallbackMethod = "error")
    public String hystrix() throws InterruptedException {
        int sleepTime = new Random().nextInt(200);
        Thread.sleep(sleepTime);
        return "sleep: " + sleepTime + " ms";
    }

    public String error() {
        return "timeout";
    }
}
