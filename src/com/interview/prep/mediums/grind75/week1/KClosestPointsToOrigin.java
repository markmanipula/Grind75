package com.interview.prep.mediums.grind75.week1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 */
public class KClosestPointsToOrigin {

    /**
     * Approach: Use a min-heap (PriorityQueue) to store all points based on their distance from the origin.
     * The distance from a point to the origin is calculated as (x^2 + y^2).
     *
     * Instead of computing the Euclidean distance (sqrt(x^2 + y^2)), we can simply compare the squared distances
     * to avoid unnecessary computations.
     *
     * Algorithm:
     * 1. Initialize a min-heap with a comparator that compares the squared distances of points.
     * 2. Add all points to the heap.
     * 3. Extract the top k elements from the heap to form the output array.
     *
     * Time Complexity: O(n log n), where n is the number of points.
     * - Adding each point to the heap takes O(log n).
     * - Adding all points: O(n log n).
     * - Extracting k elements: O(k log n).
     *
     * Space Complexity: O(n), where the heap stores all n points.
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            int distanceA = a[0] * a[0] + a[1] * a[1];
            int distanceB = b[0] * b[0] + b[1] * b[1];
            return Integer.compare(distanceA, distanceB);
        });

        for (int[] point : points) {
            minHeap.offer(point);
        }

        int[][] output = new int[k][];
        for (int i = 0; i < k; i++) {
            output[i] = minHeap.poll();
        }

        return output;
    }
}
