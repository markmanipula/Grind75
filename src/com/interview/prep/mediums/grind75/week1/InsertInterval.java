package com.interview.prep.mediums.grind75.week1;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval
 * and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
 * intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 *
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    /**
     * Approach:
     * - Iterate through the intervals array and handle three scenarios:
     *   1. **No overlap, newInterval comes before the current interval**:
     *      - Add `newInterval` to the result and update `newInterval` to the current interval.
     *   2. **No overlap, newInterval comes after the current interval**:
     *      - Add the current interval to the result.
     *   3. **Overlap detected**:
     *      - Merge `newInterval` with the current interval by updating the start to the smaller start
     *        and the end to the larger end.
     * - After the loop, add the remaining `newInterval` to the result.
     *
     * Time Complexity: O(n), where n is the number of intervals.
     * Space Complexity: O(n), due to the result list.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            // Case 1: New interval ends before the current interval starts
            if (newInterval[1] < interval[0]) {
                result.add(newInterval);
                newInterval = interval;
                // Case 2: New interval starts after the current interval ends
            } else if (newInterval[0] > interval[1]) {
                result.add(interval);
                // Case 3: Overlapping intervals, merge them
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }

        result.add(newInterval);
        return result.toArray(new int[result.size()][]);
    }
}
