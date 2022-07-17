package org.example.leetcode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class l19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        ListNode q = dummy;
        while (n > 0) {
            q = q.next;
            n--;
        }
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        System.out.println(dummy);
        p.next = p.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new l19().removeNthFromEnd(ListNode.of(1, 2, 3, 4, 5), 2));
    }
}
