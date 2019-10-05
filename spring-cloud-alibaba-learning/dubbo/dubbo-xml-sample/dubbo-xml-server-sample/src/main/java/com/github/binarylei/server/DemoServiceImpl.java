package com.github.binarylei.server;

import com.github.binarylei.dubbo.demo.api.EchoService;

/**
 * @author: leigang
 * @version: 2018-04-07
 */
public class DemoServiceImpl implements EchoService {

    @Override
    public String echo(String name) {
        return "Hello " + name;
    }
}
