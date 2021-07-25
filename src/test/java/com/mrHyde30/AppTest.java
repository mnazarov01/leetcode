package com.mrHyde30;

import com.mrHyde30.utils.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AppTest {
    private static final App app = new App();

    private static ListNode listNodeGenerator(int[] arr) {
        ListNode listNode = new ListNode();
        ListNode head = listNode;
        for (int v : arr) {
            listNode.next = new ListNode(v);
            listNode = listNode.next;
        }
        return head.next;
    }

    @Test
    public void testApp() {
        Assertions.assertNotNull(app);
    }

    @Test
    public void maxAreaOfIslandTest() {

        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
                , {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}
                , {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        Assertions.assertEquals(6, app.maxAreaOfIsland(grid));

        int[][] grid1 = {{1, 1, 0, 1, 1}
                , {1, 0, 0, 0, 0}
                , {0, 0, 0, 0, 1}
                , {1, 1, 0, 1, 1}};

        Assertions.assertEquals(3, app.maxAreaOfIsland(grid1));

    }

    @Test
    public void testIsInterleave() {

        Assertions.assertTrue(app.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assertions.assertFalse(app.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        Assertions.assertFalse(app.isInterleave("", "abc", "a"));

    }

    @Test
    public void testMaxArea() {

        Assertions.assertEquals(4, app.maxArea(5, 4, new int[]{1, 2, 4}, new int[]{1, 3}));
        Assertions.assertEquals(6, app.maxArea(5, 4, new int[]{3, 1}, new int[]{1}));

    }

    @Test
    public void testTwoSum() {
        Assertions.assertArrayEquals(app.twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
    }

    @Test
    public void testAddTwoNumbers() {

        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        Assertions.assertEquals(new ListNode(7, new ListNode(0, new ListNode(8)))
                , app.addTwoNumbers(l1, l2));

        l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        l2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(1))));

        Assertions.assertEquals(new ListNode(7, new ListNode(0, new ListNode(8, new ListNode(1))))
                , app.addTwoNumbers(l1, l2));

    }

    @Test
    public void testReverse() {

        Assertions.assertEquals(21, app.reverse(120));
        Assertions.assertEquals(123, app.reverse(321));
        Assertions.assertEquals(-123, app.reverse(-321));

    }

    @Test
    public void testMyAtoi() {

        Assertions.assertEquals(0, app.myAtoi("+-12"));
        Assertions.assertEquals(-42, app.myAtoi("   -42"));
        Assertions.assertEquals(1, app.myAtoi("+1"));
        Assertions.assertEquals(4193, app.myAtoi("4193 with words"));
        Assertions.assertEquals(0, app.myAtoi("words and 987"));
        Assertions.assertEquals(-2147483648, app.myAtoi("-91283472332"));

    }

    @Test
    public void testIsPalindrome() {

        Assertions.assertTrue(app.isPalindrome(121));
        Assertions.assertTrue(app.isPalindrome(1221));
        Assertions.assertFalse(app.isPalindrome(-121));
        Assertions.assertTrue(app.isPalindrome(145541));
        Assertions.assertFalse(app.isPalindrome(111112));

    }

    @Test
    public void findMedianSortedArrays() {
        Assertions.assertEquals(2, app.findMedianSortedArrays(new int[] {2}, new int[0]));
        Assertions.assertEquals(3, app.findMedianSortedArrays(new int[] {1, 2, 5}, new int[] {3, 4}));
        Assertions.assertEquals(4.5, app.findMedianSortedArrays(new int[] {1, 2, 10}, new int[] {3, 4, 5, 6 ,7}));
    }

    @Test
    public void testIsValid() {
        Assertions.assertTrue(app.isValid("()[]{}"));
        Assertions.assertFalse(app.isValid("([)]"));
    }

    @Test
    public void testCanConstruct() {

        Assertions.assertTrue(app.canConstruct("aa", "aab"));
        Assertions.assertFalse(app.canConstruct("aaa", "aab"));
        Assertions.assertTrue(app.canConstruct("aba", "aab"));
        Assertions.assertTrue(app.canConstruct("aa", "abcda"));
        Assertions.assertFalse(app.canConstruct("aaa", "aab"));
        Assertions.assertFalse(app.canConstruct("aaaz", "aab"));
        Assertions.assertTrue(app.canConstruct("zz", "zzz"));

    }

    @Test
    public void testMergeTwoLists() {

        Assertions.assertEquals(listNodeGenerator(new int[]{1, 2, 3, 4, 5, 6})
                , app.mergeTwoLists(listNodeGenerator(new int[]{1, 2, 3}), listNodeGenerator(new int[]{4, 5, 6})));

        Assertions.assertEquals(listNodeGenerator(new int[]{1, 2, 5, 5, 6})
                , app.mergeTwoLists(listNodeGenerator(new int[]{1, 5}), listNodeGenerator(new int[]{2, 5, 6})));

        Assertions.assertNull(app.mergeTwoLists(null, null));

    }

    @Test
    public void testRemoveDuplicates() {

        int[] arr = {0,0,1,1,1,2,2,3,3,4};
        Assertions.assertEquals(app.removeDuplicates(arr), 5);
        Assertions.assertArrayEquals(new int[] {0, 1, 2, 3, 4}, Arrays.copyOf(arr, 5));

        int[] arr1 = {1,1,1,2};
        Assertions.assertEquals(2, app.removeDuplicates(arr1));
        Assertions.assertArrayEquals(new int[] {1, 2}, Arrays.copyOf(arr1, 2));

        int[] arr2 = {1,2,2,2,3,4,4};
        Assertions.assertEquals(4, app.removeDuplicates(arr2));
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 4}, Arrays.copyOf(arr2, 4));

        int[] arr3 = {1,4,4,4};
        Assertions.assertEquals(2, app.removeDuplicates(arr3));
        Assertions.assertArrayEquals(new int[] {1, 4}, Arrays.copyOf(arr3, 2));

    }

}
