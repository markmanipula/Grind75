package com.interview.prep.anduril;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i]
 * is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
public class DailyTemperatures {

    /**
     * Approach: Brute Force.
     * Iterate through each day, and for each day, look for the next day with a higher temperature.
     * When a warmer day is found, calculate the difference in days using `j - i`, and update the result.
     * <p>
     * Time Complexity: O(n^2) - We use a nested loop, iterating over all future days for each day.
     * Space Complexity: O(1) - No extra data structures are used, aside from the output array.
     */
    public int[] dailyTemperaturesBruteForce(int[] temperatures) {
        int[] output = new int[temperatures.length];
        for (int i = 0; i < temperatures.length - 1; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    output[i] = j - i;
                    break;
                }
            }
        }
        output[output.length - 1] = 0;
        return output;
    }

    /**
     * Approach: Use a stack to keep track of temperatures and their indices in a monotonic decreasing order.
     * For each day, check if the current temperature is greater than the temperature at the top of the stack:
     * 1. If yes, calculate the difference in indices (days to wait) and update the output.
     * 2. Push the current temperature and index onto the stack.
     *
     * Time Complexity: O(n)
     * Each temperature is pushed onto the stack once and popped from the stack at most once.
     *
     * Space Complexity: O(n)
     * In the worst case (strictly decreasing temperatures), the stack will contain all the temperatures.
     */
    public int[] dailyTemperaturesOptimal(int[] temperatures) {
        int[] output = new int[temperatures.length];
        Deque<int[]> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            int currentTemp = temperatures[i];
            while (!stack.isEmpty() && currentTemp > stack.peek()[0]) {
                int[] top = stack.pop();
                int prevIndex = top[1];
                output[prevIndex] = i - prevIndex;
            }
            stack.push(new int[]{temperatures[i], i});
        }

        return output;
    }

}
