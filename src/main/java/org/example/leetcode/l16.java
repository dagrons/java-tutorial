package org.example.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/3sum-closest/
 * 先排序，然后枚举i的位置，问题就转化成为在(i+1, nums.length-1)的有序数组中求两数之和的问题了
 */
public class l16 {
    public int threeSumClosest(int[] nums, int target) {
        int ans = 0x3f3f3f3f;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (Math.abs(nums[j] + nums[k] - t) < Math.abs(ans)) {
                    ans = nums[j] + nums[k] - t;
                }
                if (nums[j] + nums[k] > t) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return ans + target;
    }
}
