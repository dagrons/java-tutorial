package org.example.leetcode;

/**
 * 注意和接雨水是不同的题目
 * 最简单的就是暴力，n^2，但应该注意到很多case是不可能成为最优解的
 * 所以我们只用枚举可能成为最优解的case就好了
 * 考点：分析哪些case才可能成为最优解
 */
public class l11 {
    public int maxArea(int[] height) {
        int ans = 0;
        int i = 0, j = height.length - 1;
        ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
        while (i < j) {
            if (height[i] < height[j]) {
                int p = i;
                while (p < height.length && height[p++] < height[i]); // 从左边找一个更高的
                i = p;
            } else {
                int q = j;
                while (q >= 0 && height[q--] < height[j]); // 从右边找一个更高的
                j = q;
            }
            if (i < j) {
                ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new l11().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(new l11().maxArea(new int[]{1, 1}));
    }
}
