package org.example.leetcode;

import junit.framework.TestCase;
import org.junit.Test;

public class l2Test extends TestCase {

    @Test
    public void testAddTwoNumbers() {
        l2 l2 = new l2();
        ListNode arr1 = ListNode.of(2, 4, 3);
        ListNode arr2 = ListNode.of(5, 6, 4);
        assertEquals(l2.addTwoNumbers(arr1, arr2), ListNode.of(7, 0, 8));
        arr1 = ListNode.of(9, 9, 9, 9, 9, 9, 9);
        arr2 = ListNode.of(9, 9, 9, 9);
        assertEquals(l2.addTwoNumbers(arr1, arr2), ListNode.of(8, 9, 9, 9, 0, 0, 0, 1));
    }
}