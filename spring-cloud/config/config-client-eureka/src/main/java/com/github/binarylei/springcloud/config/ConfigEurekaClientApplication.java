package com.github.binarylei.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * --spring.proflies.active=eureka
 *
 * @author leigang
 * @version 2019-03-23
 * @EnableEurekaClient 和 @EnableDiscoveryClient 等价
 * @EnableEurekaClient 只有注册中心是 Eureka 时才有效
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class ConfigEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaClientApplication.class, args);
    }

}
