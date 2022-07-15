package org.example.leetcode;


import java.util.HashMap;

public class l3 {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int i = 0;
        HashMap<Character, Integer> last = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!last.containsKey(c) || last.get(c) < i) {
                ans = Math.max(ans, j - i + 1);
            } else {
                i = last.get(c) + 1;
            }
            last.put(c, j);
        }
        return ans;
    }

}
