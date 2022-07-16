package org.example.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class l9 {
    boolean isPalindrome(int x) {
        Deque<Integer> q = new LinkedList<>();
        while (x > 0) {
            int r = x % 10;
            q.push(r);
            x /= 10;
        }
        while(!q.isEmpty() && q.peekFirst() == q.peekLast()) {
            q.removeFirst();
            if (q.isEmpty()) return true;
            q.removeLast();
        }
        if (q.isEmpty() || q.size() == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new l9().isPalindrome(12344321));
        System.out.println(new l9().isPalindrome(12321));
        System.out.println(new l9().isPalindrome(12312));
    }
}
