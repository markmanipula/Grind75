package com.interview.prep.mediums.grind75.week1;

/**
 * Given an integer array nums, find the
 * subarray with the largest sum, and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */
public class MaximumSubarray {

    /**
     * - Maintain two variables:
     *   1. currentSum: Tracks the sum of the current subarray.
     *   2. maxSum: Tracks the maximum sum found so far.
     * - Iterate through the array:
     *   - For each element, decide whether to include it in the current subarray or start a new subarray.
     *   - Update the maxSum if currentSum exceeds it.
     *
     * Time Complexity: O(n), where n is the length of nums.
     * Space Complexity: O(1), as no additional space is required beyond the variables.
     */
    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //this determines if we want to add the current number to the subarray
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }
}
