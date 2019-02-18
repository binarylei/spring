package com.github.binarylei.spring.annotation.componentscan;

import com.github.binarylei.spring.annotation.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.github.binarylei",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {User.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilter.class)
        }, useDefaultFilters = false
)
public class AnnotationConfig {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Assert.assertTrue(context.containsBean("user"));
    }

    // Scope 可取四个值：SCOPE_SINGLETON、SCOPE_PROTOTYPE、SCOPE_SESSION、SCOPE_REQUEST
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User user() {
        return new User();
    }

}
