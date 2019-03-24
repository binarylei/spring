package com.github.binarylei.spring.annotation;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 实现了 WebApplicationInitializer
 *
 * @author: leigang
 * @version: 2018-12-31
 */
public class MyAbstractAnnotationConfigDispatcherServletInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    // Root 容器配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // Servlet 容器配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyServletConfig.class};
    }

    // Servlet 容器配置
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // Filter 配置
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        encodingFilter.setForceRequestEncoding(true);
        encodingFilter.setForceResponseEncoding(true);

        return new Filter[]{encodingFilter};
    }
}
