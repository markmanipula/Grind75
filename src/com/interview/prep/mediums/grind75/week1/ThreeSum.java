package com.interview.prep.mediums.grind75.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum {

    /**
     * Approach: Sort the array and use a two-pointer approach.
     * Iterate through the array and fix each element as the first element of the triplet.
     * Use two pointers (left and right) to find the other two elements.
     * If the current sum is greater than 0, move the right pointer to the left.
     * If the current sum is less than 0, move the left pointer to the right.
     * Skip duplicates for the fixed element to avoid duplicate triplets.
     *
     * Time complexity: O(n^2)
     * - Sorting the array takes O(n log n).
     * - The nested loop with the two-pointer approach runs in O(n^2) in the worst case.
     * - Total: O(n^2), as the sorting step is dominated by the nested loop.
     *
     * Space complexity:
     * - O(n) for sorting the input array (depending on the sorting algorithm used).
     * - O(k) for storing unique triplets in the Set, where k is the number of unique triplets.
     * - Total: O(n + k).
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> output = new HashSet<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the current fixed number.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    output.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                } else if (sum > 0) {
                    right--; // Decrease the sum by moving the `right` pointer.
                } else {
                    left++; // Increase the sum by moving the `left` pointer.
                }
            }
        }

        return new ArrayList<>(output);
    }

}
