package com.github.binarylei.springcloud.feign_hystrix.feign.controller;

import com.github.binarylei.springcloud.feign_hystrix.feign.service.HystrixClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class FeignHystrixController {

    @Autowired
    private HystrixClientService service ;

    @GetMapping("user/{id}")
    public String getUser(@PathVariable("id") String id) {
        return service.getUser(id);
    }

}
