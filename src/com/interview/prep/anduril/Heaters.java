package com.interview.prep.anduril;

import java.util.Arrays;

/**
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 *
 * Example 1:
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 *
 * Example 2:
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
 *
 * Example 3:
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 */
public class Heaters {

    /**
     * Approach: Brute force.
     * For each house, calculate the minimum distance to any heater.
     * Then, find the maximum of these minimum distances across all houses.
     * This ensures that the largest distance any house has to its nearest heater is minimized.
     *
     * Time complexity: O(n * m), where n is the number of houses and m is the number of heaters.
     * Space complexity: O(1), as no additional data structures are used.
     */
    public int findRadiusBruteForce(int[] houses, int[] heaters) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < houses.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < heaters.length; j++) {
                min = Math.min(min, Math.abs(houses[i] - heaters[j]));
            }
            max = Math.max(max, min);
        }
        return max;
    }

    /**
     * Approach: Binary search. Sort the heaters, and for each house, use binary search to find the closest heater.
     * Track the minimum distance to a heater for each house and ensure all houses are covered.
     *
     * Time complexity: O((m + n) log m), where n is the number of houses and m is the number of heaters.
     * Space complexity: depends on the sort implementation
     */
    public int findRadiusBruteOptimal(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < houses.length; i++) {

            int min = Integer.MAX_VALUE;
            int left = 0;
            int right = heaters.length - 1;
            while (left <= right) {
                int middle = left + (right - left) / 2;
                min = Math.min(min, Math.abs(houses[i] - heaters[middle]));
                if (houses[i] > heaters[middle]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
            max = Math.max(max, min);
        }
        return max;
    }

}
