package com.github.binarylei.springcloud.feign;

import com.github.binarylei.springcloud.config.FeignHystrixConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author leigang
 * @version 2019-03-20
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(defaultConfiguration = FeignHystrixConfig.class)
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class);
    }
}
