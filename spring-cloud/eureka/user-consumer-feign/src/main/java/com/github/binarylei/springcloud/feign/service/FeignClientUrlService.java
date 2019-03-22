package com.github.binarylei.springcloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * http://localhost:10011/eureka/apps/EUREKA-HA
 * @author leigang
 * @version 2019-03-21
 */
@FeignClient(name = "FeignClientUrlService", url = "http://peer1:10011")
public interface FeignClientUrlService {

    @GetMapping("/eureka/apps/{servername}")
    String getServiceInfo(@PathVariable("servername") String servername);

}
