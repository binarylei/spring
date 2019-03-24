package com.github.binarylei.springcloud.feign_hystrix.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author leigang
 * @version 2019-03-21
 */
@FeignClient(name = "user-provider", fallback = HystrixClientFallback.class)
public interface HystrixClientService {

    @GetMapping("/user/{id}")
    String getUser(@PathVariable("id") String id);
}
