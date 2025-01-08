package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi]
 * both denote integral coordinates on the X-Y plane.
 * Initially, you are facing directly east from your position. You cannot move from your position,
 * but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle,
 * determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise.
 * Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 *
 * Example 1:
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
 *
 * Example 2:
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including the one at your location.
 *
 * Example 3:
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 */
public class MaximumNumberOfVisiblePoints {

    /**
     * Approach:
     * - Convert the Cartesian coordinates of the points into angles relative to the given location using `Math.atan2`.
     * - Handle edge cases:
     *   1. If a point is at the same location as the observer, increment a separate counter (`result`).
     * - Sort the angles and use a sliding window approach to find the maximum number of points within the given field of view.
     * - Sorting in necessary to handle the circular nature of angles.
     * - To handle circular wrapping of angles (e.g., transitioning from 359° to 0°), duplicate the sorted angle list with a 360° offset.
     *
     * Time Complexity:
     * - O(n log n): Sorting the angles dominates the complexity.
     * - Sliding window traversal is O(n), but sorting is the bottleneck.
     *
     * Space Complexity:
     * - O(n): Storing angles and their duplicates for circular wrapping.
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {

        //create an array of angles
        List<Double> angles = new ArrayList<>();
        int result = 0;

        //convert points to angle and put in list
        for (List<Integer> point : points) {
            int x = point.get(0) - location.get(0);
            int y = point.get(1) - location.get(1);

            //this means that the current location is same as the current point
            if (x == 0 && y == 0) {
                result++;
                continue;
            }

            //formula to convert points to angles
            angles.add(Math.toDegrees(Math.atan2(y, x)));
        }

        //sort array for sliding window approach
        Collections.sort(angles);
        //add 360 degrees for case when points wrap around the circle after reaching 360 degrees
        int n = angles.size();
        for(int i = 0; i < n; i++) {
            angles.add(angles.get(i) + 360);
        }

        //sliding window approach
        int start = 0;
        int count = 0;
        for (int end = 0; end < angles.size(); end++) {
            while (angles.get(end) - angles.get(start) > angle) {
                start++;
            }
            count = Math.max(count, end - start + 1);
        }

        return count + result;
    }
}
