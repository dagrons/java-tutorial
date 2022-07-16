package org.example.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * 和l12相同的思路就行了
 */
public class l13 {
    public int romanToInt(String s) {
        int ans = 0;
        int[] keyArr = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] valArr = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        for (int i = keyArr.length-1; i >= 0; i--) {
            while (s.startsWith(valArr[i])) {
                s = s.substring(valArr[i].length());
                ans += keyArr[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new l13().romanToInt("LVIII"));
        System.out.println(new l13().romanToInt("MCMXCIV"));
    }
}
