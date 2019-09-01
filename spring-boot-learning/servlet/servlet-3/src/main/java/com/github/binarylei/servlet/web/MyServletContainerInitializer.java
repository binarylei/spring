package com.github.binarylei.servlet.web;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author: leigang
 * @version: 2018-12-31
 */
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext ctx)
            throws ServletException {
        // 1. 处理感兴趣的类
        System.out.println(set);

        // 2.1. 注册 Servert
        ServletRegistration.Dynamic servlet = ctx.addServlet("myServlet", MyServlet.class);
        servlet.addMapping("/*");

        // 2.2. 注册 Listener
        ctx.addListener(MyServletContextListener.class);

        // 2.3. 注册 Filter
        FilterRegistration.Dynamic filter = ctx.addFilter("myFileter", MyFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
                true, "/*");
    }
}
