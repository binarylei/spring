package com.github.binarylei.springcloud.hystrix.test;

import java.util.Random;

/**
 * @author leigang
 * @version 2019-03-29
 */
public class RandomCommand implements Command {
    @Override
    public String run() {
        int sleepTime = new Random().nextInt(200);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "success";
    }

    @Override
    public String fallback() {
        return "fallback";
    }
}
