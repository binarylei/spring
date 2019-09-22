package com.binarylei.hystrix.collapser.service;

/**
 * @author binarylei
 * @version 2019-09-23
 */
public class User {

    private Long userId;

    public User(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
