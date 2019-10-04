package com.github.binarylei.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: leigang
 * @version: 2018-04-07
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring-context-dubbo.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }

}
