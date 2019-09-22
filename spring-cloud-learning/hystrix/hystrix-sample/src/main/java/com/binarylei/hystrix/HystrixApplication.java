package com.binarylei.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Hystrix 限流、隔断的作用
 * 注意：Tomcat线程、执行线程、fallback线程隔离
 * <p>
 * EnableHystrix 和 EnableCircuitBreaker 两个注解作用等价
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
