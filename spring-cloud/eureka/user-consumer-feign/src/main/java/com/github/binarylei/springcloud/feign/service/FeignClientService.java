package com.github.binarylei.springcloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author leigang
 * @version 2019-03-21
 */
@FeignClient("user-provider")
public interface FeignClientService {

    @GetMapping("/user/{id}")
    String getUser(@PathVariable("id") String id);

    // 如果参数是复杂参数(对象)，Feign 都会以 post 方式发出请求
    // 这种情况下提供都必须是 post 方式，否则无法请求到
//    @GetMapping("/get-uesr")
//    String getUser(User user);
}
