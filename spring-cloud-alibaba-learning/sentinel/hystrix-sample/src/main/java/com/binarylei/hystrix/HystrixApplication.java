package com.binarylei.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 注意：Tomcat线程、执行线程、fallback线程隔离
 * Hystrix 限流、隔断的作用
 *
 * @author leigang
 * @version 2019-03-19
 */
@SpringBootApplication
@EnableHystrix
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class);
    }
}
