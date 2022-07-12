package org.example.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 串行化计算2^2 + (2^2)^(2^2) + (last result)^(last_result)
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 2); // 这里只要signature符合就行了
        CompletableFuture<Integer> integerCompletableFuture1 = integerCompletableFuture.thenApplyAsync((i) -> {
            int ans = 1;
            for (int j = 1; j <= i; j++) {
                ans *= i;
            }
            return ans;
        }).thenApplyAsync((i) -> {
            int ans = 1;
            for (int j = 1; j <= i; j++) {
                ans *= i;
            }
            return ans;
        });
        System.out.println(integerCompletableFuture.get());
        System.out.println(integerCompletableFuture1.get());
    }
}
