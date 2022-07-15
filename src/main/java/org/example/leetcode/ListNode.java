package org.example.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode of(int... vals) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i : vals) {
            p.next = new ListNode(i);
            p = p.next;
        }
        return head.next;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode p = this;
        stringBuilder.append("[ ");
        while (p != null) {
            stringBuilder.append(p.val);
            stringBuilder.append(" ");
            p = p.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListNode)) {
            return false;
        }
        ListNode other = (ListNode) obj;
        ListNode p = this;
        while (p != null && other != null) {
            if (p.val != other.val) {
                return false;
            }
            p = p.next;
            other = other.next;
        }
        if (p != null || other != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int code = 0;
        ListNode p = this;
        while (p != null) {
            code = code * 31 + p.val;
        }
        return code;
    }
}
