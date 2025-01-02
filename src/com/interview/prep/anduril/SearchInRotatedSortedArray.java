package com.interview.prep.anduril;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 */
public class SearchInRotatedSortedArray {

    /**
     * Approach:
     * 1. Identify the pivot point where the sorted rotated array is "rotated."
     *    - Use binary search to find the smallest element, which represents the rotation point.
     *    - This is done in the first `while` loop where `while (left < right)` ensures we converge to the pivot.
     * 2. Determine the search range for the target based on the pivot.
     *    - If the target lies between the pivot and the end of the array, adjust the `left` pointer.
     *    - Otherwise, adjust the `right` pointer to search in the other half.
     * 3. Perform a standard binary search on the chosen range to find the target.
     *    - This is done in the second `while` loop, where `while (left <= right)` ensures we search inclusively.

     * Why `while (left < right)` in the first loop:
     * - This ensures the loop breaks when `left` equals `right`, converging on the smallest element (pivot).
     * - The condition excludes the case where both pointers overlap, making it ideal for finding the rotation point.

     * Why `while (left <= right)` in the second loop:
     * - This ensures we include both `left` and `right` indices while searching for the target.
     * - The loop terminates only when the valid search space is exhausted, which is typical in binary search.

     * Time Complexity: O(log n)
     * - The first loop to find the pivot point is O(log n).
     * - The second loop to perform binary search is O(log n).
     * - Thus, the total time complexity is O(log n).

     * Space Complexity: O(1)
     * - The algorithm uses only a constant amount of extra space.
     */
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        // Find the pivot point
        while (left < right) {
            int pivot = left + (right - left) / 2;
            if (nums[pivot] < nums[right]) {
                right = pivot; // The minimum is in the left half
            } else {
                left = pivot + 1; // The minimum is in the right half
            }
        }

        // Reset left and right
        int start = left;
        left = 0;
        right = nums.length - 1;

        // Determine the search range based on the pivot
        if (target >= nums[start] && target <= nums[right]) {
            left = start; // Target is in the rotated right half
        } else {
            right = start; // Target is in the left half
        }

        // Perform binary search
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1; // Target not found
    }
}
