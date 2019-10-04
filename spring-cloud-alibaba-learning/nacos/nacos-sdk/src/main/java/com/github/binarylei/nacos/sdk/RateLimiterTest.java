package com.github.binarylei.nacos.sdk;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author binarylei
 * @version 2019-08-31
 * @since 2.0.0
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(1);

        for (int i = 1; i < 10; i = i + 2) {
            double waitTime = limiter.acquire(i);
            System.out.println("cutTime=" + System.currentTimeMillis() +
                    " acq:" + i + " waitTime:" + waitTime);
        }
    }
}
