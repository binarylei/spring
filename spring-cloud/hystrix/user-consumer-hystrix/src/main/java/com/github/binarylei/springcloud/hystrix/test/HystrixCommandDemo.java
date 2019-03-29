package com.github.binarylei.springcloud.hystrix.test;


import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.internal.operators.OnSubscribeSingle;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 模拟 HystrixCommand
 *
 * @author leigang
 * @version 2019-03-28
 */
public class HystrixCommandDemo {

    public static void main(String[] args) {
        testRxJava();
    }

    static void testTryCatch() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        RandomCommand command = new RandomCommand();
        Future<String> future = executorService.submit(command::run);
        String result;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            result = command.fallback();
        } finally {
            executorService.shutdownNow();
        }
        System.out.println(result);
    }

    static void testRxJava() {
        Single.from(null)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer() {
                    RandomCommand command = new RandomCommand();

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        command.fallback();
                    }

                    @Override
                    public void onNext(Object o) {
                        command.run();
                    }
                });
    }

}
