package com.github.binarylei.springcloud.feign_hystrix.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * FeignClientsConfiguration
 *
 * @author leigang
 * @version 2019-03-21
 */
@Configuration
public class FeignHystrixConfig {

    // 开启后禁用 Hystrix
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

}
