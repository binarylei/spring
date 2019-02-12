package com.github.binarylei.springboot.starter.autoconfigure;

import com.github.binarylei.springboot.starter.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(User.class)
public class UserAutoConfiguration2 {

    @Bean
    public User user() {
        return new User();
    }
}
