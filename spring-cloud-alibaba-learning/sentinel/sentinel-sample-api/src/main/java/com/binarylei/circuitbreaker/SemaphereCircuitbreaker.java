package com.binarylei.circuitbreaker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author binarylei
 * @version 2019-09-15
 * @since 2.0.0
 */
public class SemaphereCircuitbreaker {

    public static void main(String[] args) {
        int permits = 10;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Semaphore semaphore = new Semaphore(permits);

        executor.submit(new Command() {
            @Override
            public Object call() throws Exception {

                if (semaphore.tryAcquire()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        return "result";
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                } else {
                    System.out.println("被限流了");
                }
                return null;
            }
        });
    }
}
