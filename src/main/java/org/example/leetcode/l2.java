package org.example.leetcode;

/**
 * 本质就是模拟加法
 */
public class l2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode();
        ListNode p = l1, q = l2, r = head;
        while (p != null || q != null) {
            int mod = 0;
            if (carry > 0) {
                mod += 1;
            }
            if (p != null) {
                mod += p.val;
                p = p.next;
            }
            if (q != null) {
                mod += q.val;
                q = q.next;
            }
            carry = mod / 10;
            mod = mod % 10;
            r.next = new ListNode(mod);

            r = r.next;
        }
        if (carry > 0) {
            r.next = new ListNode(carry);
        }
        return head.next;
    }
}
