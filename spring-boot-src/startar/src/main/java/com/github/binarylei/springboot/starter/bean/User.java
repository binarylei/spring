package com.github.binarylei.springboot.starter.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("user")
public class User {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
