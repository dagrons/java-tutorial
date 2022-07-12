package org.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 所以，在这里我们应该明白的是，Thread本质上就是一个提供了计算资源的容器，我们让Runnable的对象放入Thread中才能执行
 * 而Executor是一个容器池，我们将一批需要被执行的任务交给容器池进行调度，这样的好处是Executor能控制最大并发数，让系统不至于在
 * 频繁的线程切换中白白浪费掉CPU时间
 */
public class ExecutorsDemo {

    class Task implements Runnable {
        private final int threadId;

        public Task(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.printf("threadId: %d\n", threadId);
            try {
                Thread.sleep(100000); // 这里无论是自旋还是sleep，都不会造成Executor调度其他线程，只有执行结束或者cancel掉
                // 才能造成executor调度其他任务
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorsDemo demo = new ExecutorsDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<?>[] futures = new Future<?>[10];
        for (int i = 0; i < 10; i++) {
            Future<?> submit = executorService.submit(demo.new Task(i));
            futures[i] = submit;
        }
        Thread.sleep(5000);
        for (int i = 0; i < 5; i++) {
            futures[i].cancel(true);
        }
        Thread.sleep(5000);
        executorService.shutdown();

        System.out.println("==========CachedThreadPool========");
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService1.execute(demo.new Task(i));
        }
        Thread.sleep(5000);
        executorService1.shutdown();
    }
}
