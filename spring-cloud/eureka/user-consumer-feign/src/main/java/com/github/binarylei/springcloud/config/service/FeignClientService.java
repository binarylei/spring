package com.github.binarylei.springcloud.config.service;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author leigang
 * @version 2019-03-21
 */
@FeignClient("user-provider")
public interface FeignClientService {

    // spring mvc 注解
    //@GetMapping("/user/{id}")
    //@PathVariable("id")

    // feign 注解
    @RequestLine("GET /user/{id}")
    String getUser(@Param("id") String id);

    // 如果参数是复杂参数(对象)，Feign 都会以 post 方式发出请求
    // 这种情况下提供都必须是 post 方式，否则无法请求到
//    @GetMapping("/get-uesr")
//    String getUser(User user);

}
