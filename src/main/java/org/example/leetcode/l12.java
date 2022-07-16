package org.example.leetcode;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * 其实重点是IV， XL这些4和9的数字，重点想清楚进制转换的模拟过程
 */
public class l12 {
    public String intToRoman(int num) {
        int[] keyArr = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] valArr = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder ans = new StringBuilder();
        for (int i = keyArr.length-1; i >= 0; i--) {
            while (keyArr[i] <= num) {
                ans.append(valArr[i]);
                num -= keyArr[i];
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(new l12().intToRoman(1994));
        System.out.println(new l12().intToRoman(58));
    }
}
