package org.example.leetcode;

public class l7 {
    int reverse(int x) {
        int ans = 0;
        while(x > 0) {
            int r = x % 10;
            ans = ans * 10 + r;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new l7().reverse(123));
    }
}
