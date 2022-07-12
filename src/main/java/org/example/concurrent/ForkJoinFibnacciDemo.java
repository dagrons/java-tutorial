package org.example.concurrent;

import java.util.concurrent.RecursiveTask;

public class ForkJoinFibnacciDemo {

    class FibnacciTask extends RecursiveTask<Long> {

        long num;

        public FibnacciTask(long number) {
            this.num = number;
        }

        @Override
        protected Long compute() {
            if (num == 1 || num == 2) {
                return num;
            } else {
                FibnacciTask fibnacciTask = new FibnacciTask(num - 1);
                FibnacciTask fibnacciTask1 = new FibnacciTask(num - 2);
                fibnacciTask.fork();
                return fibnacciTask1.compute() + fibnacciTask.join(); // 注意这里并不是两个都fork，否则会浪费线程资源
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinFibnacciDemo forkJoinFibnacciDemo = new ForkJoinFibnacciDemo();
        FibnacciTask fibnacciTask = forkJoinFibnacciDemo.new FibnacciTask(50);
        System.out.println(fibnacciTask.compute());
    }
}
