package com.binarylei.circuitbreaker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author binarylei
 * @version 2019-09-15
 * @since 2.0.0
 */
public class TimeoutCircuitbreaker {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        int timeout = 100;
        Future<String> future = executor.submit(new Command() {
            @Override
            public Object call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                return "result";
            }
        });
        try {
            String result = future.get(timeout, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println("被限流了");
        }
    }
}
