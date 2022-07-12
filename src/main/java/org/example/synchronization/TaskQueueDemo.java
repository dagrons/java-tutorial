package org.example.synchronization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/*
其实多线程编程，需要注意几个点，不要让程序一直持有资源还长时间停留在临界区，如果持有锁还在临界区等待其他线程释放资源，要注意是否会产生了循环等待，
否则容易产生死锁的问题
 */

class TaskQueue {
    Queue<String> taskQueue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.taskQueue.add(s);
        System.out.printf("push %s", s);
        this.notify();
    }

    public synchronized String getTask() {
        while(taskQueue.isEmpty()) { // 这里其实出现了在临界区内持有资源不释放，而且还在等待其他线程addTask，但其他线程没有资源根本没法进入临界区
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return taskQueue.remove();
    }
}

public class TaskQueueDemo {
    static TaskQueue taskQueue = new TaskQueue();
    static AtomicInteger cnt = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread pushWorker = new Thread() {
            public void run() {
                while (true) {
                    taskQueue.addTask(String.format("%d", cnt.addAndGet(1)));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        pushWorker.start();
        Thread popWorker = new Thread() {
            public void run() {
                while (true) {
                    System.out.printf("pop %s", taskQueue.getTask());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        popWorker.start();
        pushWorker.join();
        popWorker.join();
    }
}
