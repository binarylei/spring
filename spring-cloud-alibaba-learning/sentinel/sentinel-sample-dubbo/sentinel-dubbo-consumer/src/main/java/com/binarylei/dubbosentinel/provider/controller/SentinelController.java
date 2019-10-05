package com.binarylei.dubbosentinel.provider.controller;

import com.binarylei.dubbosentinel.api.SentinelService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Reference(url = "dubbo://127.0.0.1:20880/")
    private SentinelService sentinelService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return sentinelService.sayHello(name);
    }

    @GetMapping("/sayHelloLimit/{name}")
    public String sayHelloLimit(@PathVariable("name") String name) {
        RpcContext.getContext().setAttachment("dubboApplication", "sentinel-web");
        return sentinelService.sayHello(name);
    }

}
