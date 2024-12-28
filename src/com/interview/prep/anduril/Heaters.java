package com.interview.prep.anduril;

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
}
