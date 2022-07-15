package org.example.leetcode;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;

public class l1Test extends TestCase {
    @Test
    public void testTwoSum() {
        l1 l1 = new l1();
        int[] ints = Arrays.stream(l1.twoSum(new int[]{2, 1, 4, 3, 5}, 5)).sorted().toArray();
        assertArrayEquals(ints, new int[]{1, 2});
    }
}