package org.example.leetcode;

/**
 * 中心扩展法
 */
public class l5 {
    public String longestPalindrome(String s) {
        char[] ss = s.toCharArray();
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数扩展
            int p = i, q = i;
            while (p >= 0 && q <s.length() && ss[p] == ss[q]) {
                p --;
                q ++;
            }
            if (q-1 - p - 1 + 1 >= r - l + 1) {
                l = p + 1;
                r = q - 1;
            }
            // 偶数扩展
            p = i;
            q = i + 1;
            while (p >= 0 && q < s.length() && ss[p--] == ss[q++]) {
                p --;
                q ++;
            }
            if (q - 1 - p - 1 + 1 >= r -l + 1) {
                l = p + 1;
                r = q - 1;
            }

        }
        return s.substring(l, r + 1);
    }

    public static void main(String[] args) {
        System.out.println(new l5().longestPalindrome("babad"));
        System.out.println(new l5().longestPalindrome("cbbd"));
    }
}
