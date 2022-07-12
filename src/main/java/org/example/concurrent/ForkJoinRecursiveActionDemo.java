package org.example.concurrent;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;


/**
 * 实现一个并行合并排序
 */
public class ForkJoinRecursiveActionDemo {

    class MergetSortTask extends RecursiveAction {
        private final int[] arr;

        MergetSortTask(int[] arr) {
            this.arr = arr;
        }

        @Override
        protected void compute() {
            // smaller enough
            if (arr.length < 2) {
                return;
            }
            // not small enough, let's divide and merge
            int mid = arr.length / 2;

            int[] leftArr = new int[mid];
            System.arraycopy(arr, 0, leftArr, 0, mid);
            int[] rightArr = new int[arr.length - mid];
            System.arraycopy(arr, mid, rightArr, 0, arr.length - mid);

            invokeAll(new MergetSortTask(leftArr), new MergetSortTask(rightArr));
            int i = 0, j = 0, k = 0;
            while (i < mid || j < arr.length - mid) {
                if (j >= arr.length - mid) {
                    arr[k++] = leftArr[i++];
                } else if (i >= mid) {
                    arr[k++] = rightArr[j++];
                } else {
                    if (leftArr[i] < rightArr[j]) {
                        arr[k++] = leftArr[i++];
                    } else {
                        arr[k++] = rightArr[j++];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinRecursiveActionDemo forkJoinDemo = new ForkJoinRecursiveActionDemo();
        int arr[] = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        MergetSortTask mergetSortTask = forkJoinDemo.new MergetSortTask(arr);
        mergetSortTask.invoke();
        for (int i = 0; i < arr.length; i++) {
            int i1 = arr[i];
            System.out.print(i1);
        }
    }
}
