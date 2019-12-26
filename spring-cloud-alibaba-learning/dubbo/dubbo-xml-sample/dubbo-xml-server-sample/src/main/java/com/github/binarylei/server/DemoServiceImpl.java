package com.github.binarylei.server;

import binarylei.dubbo.api.EchoService;

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
