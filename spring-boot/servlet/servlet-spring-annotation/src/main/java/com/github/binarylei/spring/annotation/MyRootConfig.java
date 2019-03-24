package com.github.binarylei.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author: leigang
 * @version: 2018-12-31
 */
@Configuration
@ComponentScan(basePackages = "com.github.binarylei",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class MyRootConfig {
}
