package org.example.leetcode;

import junit.framework.TestCase;
import org.junit.Test;

public class l3Test extends TestCase {
    @Test
    public void testLengthOfLongestSubstring() {
        l3 l3 = new l3();
        assertEquals(l3.lengthOfLongestSubstring("pwwkew"), 3);
        assertEquals(l3.lengthOfLongestSubstring("abcabcbb"), 3);
    }
}