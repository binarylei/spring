package com.binarylei.guaua;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author binarylei
 * @version 2019-09-13
 * @since 2.0.0
 */
public class RateLimiterTest {

    // 基于 QPS 获取令牌
    private static RateLimiter rateLimiter = RateLimiter.create(10);

    public static void doTest() {
        if (rateLimiter.tryAcquire()) {
            System.out.println("获取令牌成功");
        } else {
            System.out.println("获取令牌失败，被限流了");
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    doTest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
