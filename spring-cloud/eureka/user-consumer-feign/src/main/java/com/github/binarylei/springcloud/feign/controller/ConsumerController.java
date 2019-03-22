package com.github.binarylei.springcloud.feign.controller;

import com.github.binarylei.springcloud.feign.service.FeignClientService;
import com.github.binarylei.springcloud.feign.service.FeignClientUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class ConsumerController {

    @Autowired
    private FeignClientService feignClientService;

    @Autowired
    private FeignClientUrlService feignClientUrlService;

    @GetMapping("feignClientService/{id}")
    public String eurekaClient(@PathVariable("id") String id) {
        return feignClientService.getUser(id);
    }

    @GetMapping("service-info/{servicename}")
    public String serviceInfo(@PathVariable("servicename") String servicename) {
        return feignClientUrlService.getServiceInfo(servicename);
    }
}
