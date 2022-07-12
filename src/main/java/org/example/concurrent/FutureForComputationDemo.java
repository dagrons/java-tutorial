package org.example.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * compute 1^2 + 2^2 + 3^3 + ... + 10^10
 * 多线程执行，然后并拢
 */
public class FutureForComputationDemo {
    class SquareTask implements Callable<Integer> {
        private final int number;

        public SquareTask(int number) {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception {
            int ans = 1;
            for (int i = 1; i <= number; i++) {
                ans *= number;
            }
            return ans;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int ans = 0;

        FutureForComputationDemo computationDemo = new FutureForComputationDemo();
        ArrayList<Future<Integer>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            futures.add(executorService.submit(computationDemo.new SquareTask(i)));
        }

        for (Iterator<Future<Integer>> iterator = futures.iterator(); iterator.hasNext(); ) {
            Future<Integer> next =  iterator.next();
            ans += next.get();
        }
        System.out.println(ans);
    }
}
