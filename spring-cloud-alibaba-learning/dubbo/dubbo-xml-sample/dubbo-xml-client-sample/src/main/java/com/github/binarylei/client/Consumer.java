package com.github.binarylei.client;

import binarylei.dubbo.api.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: leigang
 * @version: 2018-04-07
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:spring-context-dubbo.xml"});
        context.start();
        EchoService demoService = (EchoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.echo("world");   // 执行远程方法
        System.out.println( hello );                // 显示调用结果
        System.in.read();
    }
}
