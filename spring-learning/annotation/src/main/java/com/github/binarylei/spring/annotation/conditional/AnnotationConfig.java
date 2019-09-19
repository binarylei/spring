package com.github.binarylei.spring.annotation.conditional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.github.binarylei")
public class AnnotationConfig {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Assert.assertTrue(context.containsBean("testString"));
        Assert.assertFalse(context.containsBean("testString1"));
    }

    @Bean("testString")
    @ConditionalOnClass(name = "java.lang.String")
    public String testString() {
        return "testString";
    }

    @Bean("testString1")
    @ConditionalOnClass(name = "java.lang.String1")
    public String testString1() {
        return "testString1";
    }
}
