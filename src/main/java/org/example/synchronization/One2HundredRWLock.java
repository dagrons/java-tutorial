package org.example.synchronization;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class One2HundredRWLock {

    private volatile int counter = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock rlock = lock.readLock();
    private Lock wlock = lock.writeLock();

    class Printer implements Runnable {

        private final int threadId;

        public Printer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                rlock.lock(); // 用rlock和wlock拆分临界区
                if (counter > 100) {
                    return;
                }
                if (counter % 10 != threadId) {
                    rlock.unlock();
                } else {
                    wlock.lock();
                    System.out.printf("threadId %d: %d", threadId, counter++);
                    wlock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        One2HundredDemoV2 one2HundredDemoV2 = new One2HundredDemoV2();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(one2HundredDemoV2.new Printer(i));
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
    }
}
