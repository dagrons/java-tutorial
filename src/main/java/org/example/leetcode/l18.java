package org.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/4sum/
 * [-2, -1, 0, 0, 1, 2]
 * 0
 */
public class l18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long t = (long) target - (long) nums[i] - (long) nums[j];
                int p = j + 1;
                int q = nums.length - 1;
                while (p < q) {
                    if (nums[p] + nums[q] == t) {
                        ans.add(List.of(nums[i], nums[j], nums[p], nums[q]));
                    }
                    if (nums[p] + nums[q] > t) {
                        while (q - 1 > p && nums[q] == nums[q - 1]) {
                            q--;
                        }
                        q--;
                    } else {
                        while (p + 1 < q && nums[p] == nums[p + 1]) {
                            p++;
                        }
                        p++;
                    }
                }
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    j++;
                }
            }
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return ans;
    }
}
