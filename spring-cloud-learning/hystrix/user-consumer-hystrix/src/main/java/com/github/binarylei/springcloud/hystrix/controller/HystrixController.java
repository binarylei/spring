package com.github.binarylei.springcloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class HystrixController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("hystrix")
    @HystrixCommand(
            commandProperties = {@HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            fallbackMethod = "timeout")
    public String hystrix(@RequestParam int costTime) throws InterruptedException {
        Thread.sleep(costTime);
        logger.info("hystrix info");
        return "sleep: " + costTime + " ms";
    }

    public String timeout(@RequestParam int costTime) {
        logger.error("hystrix error");
        return "timeout";
    }
}
