package com.binarylei.jdk;

import java.util.concurrent.Semaphore;

/**
 * @author binarylei
 * @version 2019-09-13
 * @since 2.0.0
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("acquire running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    semaphore.release();
                }
            }).start();
        }
    }
}
