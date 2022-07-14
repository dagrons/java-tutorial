package org.example.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 串行化计算2^2 + (2^2)^(2^2) + (last result)^(last_result)
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 2); // 这里只要signature符合就行了
        for (int i = 0; i < 2; i++) {
            integerCompletableFuture = integerCompletableFuture.thenApplyAsync(t->{
                int ans = 1;
                for (int j = 1; j <= t; j++) {
                    ans *= t;
                }
                System.out.printf("%d, %d\n", t, ans);
                return ans;
            });
        }
        System.out.println(integerCompletableFuture.get());
    }
}
