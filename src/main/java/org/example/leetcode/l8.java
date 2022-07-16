package org.example.leetcode;

public class l8 {
    int myAtoi(String s) {
        int ans = 0;
        char[] arr = s.toCharArray();
        int isNegative = 0;
        if (arr[0] == '-') {
            isNegative = 1;
        }
        for (int i = isNegative; i < arr.length; i++) {
            char c = arr[i];
            ans = ans * 10 + c - '0';
        }
        if (isNegative > 0) {
            return -ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new l8().myAtoi("-214124"));
    }
}
