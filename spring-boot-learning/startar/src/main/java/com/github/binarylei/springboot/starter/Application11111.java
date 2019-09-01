package com.github.binarylei.springboot.starter;

import org.junit.Test;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class Application11111 {

    @Test
    public void test() throws IOException {
        List<PropertySource<?>> list = new PropertiesPropertySourceLoader().load(
                "xxx", new ClassPathResource("application.properties"));
        for (PropertySource<?> propertySource : list) {
            String s = new Binder(ConfigurationPropertySources.from(propertySource))
                    .bind("user.enabled", String.class)
                    .get();
            System.out.println(s);
        }

    }
}
