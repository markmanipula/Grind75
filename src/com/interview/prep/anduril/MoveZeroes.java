package com.interview.prep.anduril;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 */
public class MoveZeroes {

    /**
     * Approach:
     * - Use two pointers:
     *   1. `left` pointer starts at the beginning and identifies zeros to be replaced.
     *   2. `right` pointer iterates through the array to find non-zero elements.
     * - Whenever a non-zero element is found, swap it with the zero at `left`, then increment `left`.
     *
     * Time Complexity:
     * - O(n): Two passes through the array (one for swapping and one for filling zeros).
     *
     * Space Complexity:
     * - O(1): In-place operations, no extra space used.
     */
    public void moveZeroes(int[] nums) {

        int left = 0;
        while (left < nums.length && nums[left] != 0) {
            left++;
        }

        for (int right = left; right < nums.length; right++) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                nums[right] = 0;
                left++;
            }
        }

    }
}
