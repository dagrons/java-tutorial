package org.example.leetcode;

import junit.framework.TestCase;
import org.junit.Test;

public class l15Test extends TestCase {
    @Test
    public void testThreeSum() {
        assertEquals(new l15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}), new int[][]{
                {-1, -1, 2},
                {-1, 0, 1}
        });
    }
}