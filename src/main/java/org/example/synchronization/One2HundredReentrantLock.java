package org.example.synchronization;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁属于比较低级的抽象，在实际中，我们往往需要实现比较高级的抽象
 */
public class One2HundredReentrantLock {
    private volatile int counter = 0;
    private ReentrantLock lock = new ReentrantLock();

    class Printer implements Runnable {
        public final int threadId;

        public Printer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                if (lock.tryLock()) {
                    if (counter > 100) { // 为什么这里需要重复判断，因为while(counter < 100)和lock.lock()并不具有原子性
                        // 说白了，这里其实是compare and lock
                        // 但并没有compare and lock这样的操作，于是只能是lock and compare，如果compare符合，就执行，如果不符合就立即释放锁
                        return;
                    }
                    if (counter % 10 != threadId) {
                        lock.unlock();
                    } else {
                        System.out.printf("thread %d: %d\n", threadId, counter++);
                        lock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        One2HundredReentrantLock one2HundredReentrantLock = new One2HundredReentrantLock();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(one2HundredReentrantLock.new Printer(i));
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
    }
}
