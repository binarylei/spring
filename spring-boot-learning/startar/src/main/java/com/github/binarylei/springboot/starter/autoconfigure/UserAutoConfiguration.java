package com.github.binarylei.springboot.starter.autoconfigure;

import com.github.binarylei.springboot.starter.bean.UserProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "user", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfiguration {
}
