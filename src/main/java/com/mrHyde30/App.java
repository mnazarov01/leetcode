package com.mrHyde30;

import com.mrHyde30.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {

    }

    // https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {

        if (grid.length == 0) return 0;

        int max = 0;
        short m = (short) grid.length;
        short n = (short) grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max,  deepFirst(grid, i, j));
            }

        }

        return max;
    }

    private int deepFirst(int[][] grid, int i, int j) {

        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;

        return 1 + deepFirst(grid, i, j - 1)
                + deepFirst(grid, i + 1, j)
                +  deepFirst(grid, i, j + 1)
                + deepFirst(grid, i - 1, j);

    }


    // https://leetcode.com/problems/interleaving-string/

    public boolean isInterleave(String s1, String s2, String s3) {

        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];

    }

    // https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int hl = horizontalCuts.length;
        int vl = verticalCuts.length;

        long maxHorizontal = Math.max(horizontalCuts[0], h - horizontalCuts[hl - 1]);
        for (int i = 1; i < hl; i++) {
            maxHorizontal = Math.max(maxHorizontal, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        long maxVertical = Math.max(verticalCuts[0], w - verticalCuts[vl - 1]);
        for (int j = 1; j < vl; j++) {
            maxVertical = Math.max(maxVertical, verticalCuts[j] - verticalCuts[j - 1]);
        }

        return (int) ((maxHorizontal * maxVertical) % 1000000007);

    }

    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int targetNum = target - nums[i];
            if (map.containsKey(targetNum)) {
                return new int[]{map.get(targetNum), i};
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[0];
    }

    // https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode listNode = new ListNode();
        ListNode lastTarget = listNode;

        boolean addOne = false;
        while (l1 != null || l2 != null || addOne) {

            int sum = (l1 == null ? 0 : l1.val)
                    + (l2 == null ? 0 : l2.val)
                    + (addOne ? 1 : 0);

            if (sum > 9) {
                sum -= 10;
                addOne = true;
            } else {
                addOne = false;
            }

            lastTarget.next = new ListNode(sum);

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            lastTarget = lastTarget.next;

        }

        return listNode.next;

    }

    // https://leetcode.com/problems/reverse-integer/
    public int reverse(int x) {

        int result = 0;
        int pop;

        while (x != 0) {

            pop = x % 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            result = result * 10 + pop;
            x /= 10;
        }
        return result;
    }

    // https://leetcode.com/problems/string-to-integer-atoi/
    public int myAtoi(String s) {

        int i = 0;
        int sign = 1;
        int answer = 0;

        if (s.length() == 0) return 0;

        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i++) == '-' ? -1 : 1;
        }

        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            if (answer > Integer.MAX_VALUE / 10 || answer == Integer.MAX_VALUE / 10
                    && s.charAt(i) - '0' > Integer.MAX_VALUE % 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            answer = answer * 10 + (s.charAt(i++) - '0');
        }
        return answer * sign;
    }

    // https://leetcode.com/problems/palindrome-number/
    public boolean isPalindrome(int x) {

        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int reverseInteger = 0;
        while (x > reverseInteger) {
            reverseInteger = reverseInteger * 10 + (x % 10);
            x /= 10;
        }
        return x == reverseInteger || x == reverseInteger / 10;

    }

    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // maxLeftX <= minRightY
        // maxLeftY <= minRightX

        // if maxLeftX > minRightY
        // move toward left in x
        // else move toward right in x

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = nums1.length;

        while (low <= high) {

            int positionX = (low + high) / 2; // 0
            int positionY = ((x + y + 1) / 2) - positionX;

            int maxLeftX = positionX == 0 ? Integer.MIN_VALUE : nums1[positionX - 1];
            int minRightX = positionX == x ? Integer.MAX_VALUE : nums1[positionX];

            int maxLeftY = positionY == 0 ? Integer.MIN_VALUE : nums2[positionY - 1];
            int minRightY = positionY == y ? Integer.MAX_VALUE : nums2[positionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = positionX - 1;
            } else {
                low = positionX + 1;
            }

        }

        throw new IllegalArgumentException();

    }


    // https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {

        Deque<Character> deque = new ArrayDeque<>();
        boolean ans = true;

        for (char character : s.toCharArray()) {
            if (character == '(' || character == '{' || character == '[') {
                deque.offer(character);
            } else if (deque.size() == 0) {
                ans = false;
                break;
            } else if (deque.peekLast() == '(' && character == ')'
                    || deque.peekLast() == '{' && character == '}' || deque.peekLast() == '[' && character == ']') {
                deque.pollLast();
            } else {
                ans = false;
                break;
            }
        }
        return ans && deque.size() == 0;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        int[] letters = new int[26];
        for (char ch : magazine.toCharArray())
            letters[ch - 'a']++;

        for (char ch : ransomNote.toCharArray()) {
            if (--letters[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){

        if (l1 == null && l2 == null) return null;

        ListNode list = new ListNode();
        ListNode head = list;

        while (l1 != null && l2 != null) {

            if (l1.val > l2.val) {
                list.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                list.next = new ListNode(l1.val);
                l1 = l1.next;
            }

            list = list.next;

        }

        if (l1 != null) list.next = l1;
        if (l2 != null) list.next = l2;

        return head.next;

    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) return 0;
        int unIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[unIndex] != nums[i]) {
                unIndex++;
                nums[unIndex] = nums[i];

            }
        }
        return ++unIndex;
    }

}

