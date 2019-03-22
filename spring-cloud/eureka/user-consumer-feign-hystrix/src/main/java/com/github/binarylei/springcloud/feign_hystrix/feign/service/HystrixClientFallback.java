package com.github.binarylei.springcloud.feign_hystrix.feign.service;

import org.springframework.stereotype.Component;

/**
 * @author leigang
 * @version 2019-03-21
 */
@Component
public class HystrixClientFallback implements HystrixClientService {

    @Override
    public String getUser(String id) {
        return "fallback";
    }

}
