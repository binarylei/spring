package com.github.binarylei.springboot.starter.autoconfigure;

import com.github.binarylei.springboot.starter.bean.PersonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(PersonProperties.class)
public class PersonAutoConfiguration {

    @Bean
    public PersonProperties person() {
        return new PersonProperties();
    }
}
