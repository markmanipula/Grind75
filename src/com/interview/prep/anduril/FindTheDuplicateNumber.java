package com.interview.prep.anduril;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 * Constraints:
 *     1 <= n <= 105
 *     nums.length == n + 1
 *     1 <= nums[i] <= n
 *     All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 * Follow up:
 *     How can we prove that at least one duplicate number must exist in nums?
 *     Can you solve the problem in linear runtime complexity?
 */
public class FindTheDuplicateNumber {

    /**
     * Approach:
     * - Use Floyd’s Tortoise and Hare (Cycle Detection) algorithm.
     * - Treat the array indices and values as a linked list where nums[i] points to nums[nums[i]].
     * - If a duplicate exists, it will create a cycle.
     * - Use a slow pointer (`slow`) and a fast pointer (`fast`) to detect the cycle.
     * - Once a cycle is detected, find the entry point of the cycle, which is the duplicate number.
     *
     * Time Complexity: O(n)
     * - Each step in finding the cycle and entry point takes linear time.
     *
     * Space Complexity: O(1)
     * - Only uses two pointers for traversal.
     */
    public int findDuplicate(int[] nums) {

        // Step 1: Detect cycle using slow and fast pointers
        int slow = nums[0];
        int fast = nums[0];

        slow = nums[slow];
        fast = nums[nums[fast]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Step 2: Find the entry point of the cycle (duplicate number)
        int slow2 = nums[0];
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        return slow; // The duplicate number
    }

}
