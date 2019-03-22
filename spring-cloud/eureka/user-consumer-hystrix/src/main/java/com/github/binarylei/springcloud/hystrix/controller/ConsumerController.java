package com.github.binarylei.springcloud.hystrix.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class ConsumerController {

    @Autowired
    private EurekaClient eurekaClient;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("hystrix")
    @HystrixCommand(fallbackMethod = "error")
    public String eurekaClient() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("user-provider", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        return restTemplate.getForObject(homePageUrl + "/user/2", String.class);
    }

    public String error() {
        return "error";
    }
}
