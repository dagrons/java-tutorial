package org.example.leetcode;

import java.util.HashMap;

/**
 * 两数之和
 */
public class l1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> st = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (st.containsKey(target - num)) {
                return new int[]{i, st.get(target-num)};
            }
            st.put(num, i);
        }
        return null;
    }
}
