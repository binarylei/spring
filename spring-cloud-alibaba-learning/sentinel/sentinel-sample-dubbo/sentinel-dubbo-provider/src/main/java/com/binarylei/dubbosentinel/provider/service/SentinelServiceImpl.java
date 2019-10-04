package com.binarylei.dubbosentinel.provider.service;

import com.binarylei.dubbosentinel.api.SentinelService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class SentinelServiceImpl implements SentinelService {


    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }

}
