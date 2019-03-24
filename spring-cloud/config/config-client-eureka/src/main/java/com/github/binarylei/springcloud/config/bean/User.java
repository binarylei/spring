package com.github.binarylei.springcloud.config.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author leigang
 * @version 2019-03-23
 */
@ConfigurationProperties("test.user")
public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
