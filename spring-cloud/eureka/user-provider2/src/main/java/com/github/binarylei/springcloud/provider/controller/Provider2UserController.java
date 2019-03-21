package com.github.binarylei.springcloud.provider.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class Provider2UserController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("user/{id}")
    public String user(@PathVariable("id") String id) {
        return id;
    }

    @GetMapping("url-info")
    public String info() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("EUREKA-CLIENT", false);
        return instance.getHomePageUrl();
    }
}