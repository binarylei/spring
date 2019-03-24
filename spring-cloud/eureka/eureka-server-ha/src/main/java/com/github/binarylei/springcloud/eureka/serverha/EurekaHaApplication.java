package com.github.binarylei.springcloud.eureka.serverha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author leigang
 * @version 2019-03-25
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHaApplication.class);
    }
}
