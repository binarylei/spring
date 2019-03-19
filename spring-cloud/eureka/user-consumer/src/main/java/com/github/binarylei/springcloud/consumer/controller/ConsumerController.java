package com.github.binarylei.springcloud.consumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
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

    @GetMapping("restTemplate")
    public String restTemplate() {
        return restTemplate.getForObject("http://localhost:10002/user/2", String.class);
    }

    @GetMapping("eurekaClient")
    public String eurekaClient() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("user-provider", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        return restTemplate.getForObject(homePageUrl + "/user/2", String.class);
    }
}
