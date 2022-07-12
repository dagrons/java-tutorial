package org.example.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class One2HundredCondition {
    private volatile int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    class Printer implements Runnable {

        private int threadId;

        public Printer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                if (lock.tryLock()) {
                    if (counter > 100) {
                        return;
                    }
                    if (counter % 10 != threadId) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.printf("threadid %d: %d\n", threadId, counter++);
                        condition.signalAll();
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        One2HundredCondition one2HundredCondition = new One2HundredCondition();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(one2HundredCondition.new Printer(i));
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
    }
}
