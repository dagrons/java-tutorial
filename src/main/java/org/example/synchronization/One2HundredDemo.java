package org.example.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/*
从1打印到100
问题的关键其实是"让出资源，等待资源，打印"这样的模式
对于线程1：获取资源，打印，让出资源
对于线程2：获取资源，打印，让出资源
其实notify+wait才是一个完整的让出资源的过程，而synchronized才是获取资源的过程
 */
public class One2HundredDemo {
    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger cnt = new AtomicInteger(0);
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (cnt) {
                    while (cnt.get() == 0) {
                        cnt.notifyAll();
                        try {
                            cnt.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (cnt.get() < 100) {
                        System.out.printf("thread1: %d\n", cnt.getAndAdd(1));
                        cnt.notifyAll();
                        try {
                            cnt.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (cnt) {
                    while (cnt.get() < 100) {
                        System.out.printf("thread2: %d\n", cnt.getAndAdd(1));
                        cnt.notifyAll();
                        try {
                            cnt.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
