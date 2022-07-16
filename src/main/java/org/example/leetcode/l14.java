package org.example.leetcode;

public class l14 {
    public String longestCommonPrefix(String[] strs) {
        int i = 0;
        while (true) {
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || i >= strs[j-1].length() || strs[j].charAt(i) != strs[j-1].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println(new l14().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(new l14().longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }
}