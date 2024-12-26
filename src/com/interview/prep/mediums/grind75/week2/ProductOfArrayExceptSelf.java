package com.interview.prep.mediums.grind75.week2;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class ProductOfArrayExceptSelf {

    /**
     * Approach:
     * Use two auxiliary arrays to store prefix and suffix products:
     * - The `prefix` array stores cumulative products of elements from the start to each index.
     * - The `suffix` array stores cumulative products of elements from the end to each index.
     *
     * For each index, the result is calculated as `prefix[i - 1] * suffix[i + 1]`.
     * - Edge cases (first and last elements) are handled by using a default value of 1.
     *
     * Time Complexity: O(n), where n is the length of nums.
     * Space Complexity: O(n), due to the additional `prefix` and `suffix` arrays.
     */
    public int[] productExceptSelf(int[] nums) {

        int[] prefix = new int[nums.length];
        int prefixProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            prefixProduct *= nums[i];
            prefix[i] = prefixProduct;
        }

        int[] suffix = new int[nums.length];
        int suffixProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            suffixProduct *= nums[i];
            suffix[i] = suffixProduct;
        }

        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //uses a default value of 1 to multiple to the prefix/suffix value of array
            int pre = i == 0 ? 1 : prefix[i - 1];
            int suf = i == nums.length - 1 ? 1 : suffix[i + 1];
            output[i] = pre * suf;
        }
        return output;
    }
}
