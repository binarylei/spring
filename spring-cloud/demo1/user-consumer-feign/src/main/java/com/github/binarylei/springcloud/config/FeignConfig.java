package com.github.binarylei.springcloud.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

/**
 * FeignClientsConfiguration 默认使用 Spring mvc 契约
 *
 * @author leigang
 * @version 2019-03-21
 */
@Configuration
public class FeignConfig {

    @Bean
    public Contract feignContract(ConversionService feignConversionService) {
        return new Contract.Default();
    }

    // 配置用户名和密码
    @Bean
    public BasicAuthenticationInterceptor basicAuthenticationInterceptor() {
        return new BasicAuthenticationInterceptor("username", "password");
    }

    // 必须在 debug 时都会有日志输出
    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
