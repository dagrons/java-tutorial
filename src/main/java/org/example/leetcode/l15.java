package org.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class l15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        HashMap<Integer, Integer> preMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            preMap.put(-nums[i], i);
        }

        int i = 0;
        while (i < nums.length) {
            for (int j = nums.length-1; j >= i ; j--) {
                int target = nums[i] + nums[j];
                if (preMap.containsKey(target) && preMap.get(target) > i && preMap.get(target) < j) {
                    ans.add(List.of(nums[i], nums[preMap.get(target)], nums[j]));
                }
            }
            i++;
            while (i < nums.length && nums[i] == nums[i-1]) {
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new l15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
