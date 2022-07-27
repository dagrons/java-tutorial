package org.example.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 分别尝试i=20, i=30， i=31
 * Executors.newFixedThreadPool和newCachedThreadPool都是选用固定的参数，没有限制blockingQueue的大小
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        for (int i = 0; i < 30; i++) { // 对于30个线程，0-4被放置在corePool中，然后后面的在blockingQueue中，最后的在maximumPool中
            final int j = i;
            threadPoolExecutor.execute(() -> {
                while (true) {
                    System.out.println(j);
                    try {
                        Thread.sleep(1000000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
