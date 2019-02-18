package com.github.binarylei.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 或实现 WebMvcConfigurer 接口
 *
 * @author: leigang
 * @version: 2018-12-31
 */
@Configuration
@ComponentScan(basePackages = "com.github.binarylei",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class MyServletConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
