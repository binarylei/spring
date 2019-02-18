package com.github.binarylei.spring.annotation.config;

import com.github.binarylei.spring.annotation.bean.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.github.binarylei",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}
)
@Lazy(false)
public class AnnnotationConfig {

    // Scope 可取四个值：SCOPE_SINGLETON、SCOPE_PROTOTYPE、SCOPE_SESSION、SCOPE_REQUEST
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User user() {
        return new User();
    }
}
