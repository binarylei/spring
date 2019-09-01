package com.github.binarylei.springcloud.hystrix.test;

/**
 * @author leigang
 * @version 2019-03-29
 */
public interface Command {
    String run();

    String fallback();
}
