package com.github.binarylei.springboot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        System.setProperty(ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY,
//                "classpath://application.properties");
//        System.setProperty(ConfigFileApplicationListener.INCLUDE_PROFILES_PROPERTY, "test3");
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
//        application.setAdditionalProfiles("test2");
        System.setProperty(ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY, "test1");
        ConfigurableApplicationContext context = application.run(new String[]{"aaa"});

        ConfigurableEnvironment environment = context.getEnvironment();
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            System.out.println(propertySource);
        }

    }
}
