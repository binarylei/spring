package com.github.binarylei.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author leigang
 * @version 2019-03-19
 */
@SpringBootApplication
@EnableEurekaClient
public class Provider2Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider2Application.class);
    }
}
