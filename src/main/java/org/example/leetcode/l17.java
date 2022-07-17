package org.example.leetcode;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class l17 {
    public List<String> letterCombinations(String digits) {
        if (Strings.isNullOrEmpty(digits)) {
            return List.of();
        }
        List<String> ans = new ArrayList<>();
        Map<Integer, String> preMap = Map.of(1, "", 2, "abc", 3, "def", 4, "ghi", 5, "jkl", 6, "mno", 7, "pqrs", 8, "tuv", 9, "wxyz");
        char[] intArr = digits.toCharArray();
        dfs(ans, new StringBuilder(), 0, intArr, preMap);
        return ans;
    }

    public void dfs(List<String> ans, StringBuilder vec, int t, char[] intArr, Map<Integer, String> preMap) {
        if (t == intArr.length) {
            ans.add(vec.toString());
            return;
        }
        String s = preMap.get(intArr[t] - '0');
        for (int i = 0; i < s.length(); i++) {
            vec.append(s.charAt(i));
            dfs(ans, vec, t+1, intArr, preMap);
            vec.deleteCharAt(vec.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new l17().letterCombinations("23"));
    }
}
