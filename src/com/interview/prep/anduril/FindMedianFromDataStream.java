package com.interview.prep.anduril;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 *     For example, for arr = [2,3,4], the median is 3.
 *     For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 *
 *     MedianFinder() initializes the MedianFinder object.
 *     void addNum(int num) adds the integer num from the data stream to the data structure.
 *     double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */
public class FindMedianFromDataStream {

    /**
     * Approach:
     * - Two heaps are used:
     *   1. A max heap (leftHeap) to store the smaller half of the numbers.
     *   2. A min heap (rightHeap) to store the larger half of the numbers.
     *
     * - Numbers are added to the appropriate heap and the heaps are rebalanced to ensure
     *   that the size difference between them is at most 1.
     *
     * Time Complexity:
     * - addNum: O(log n) for heap operations.
     * - findMedian: O(1).
     *
     * Space Complexity: O(n) to store the numbers in the heaps.
     */

    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;

    public FindMedianFromDataStream() {
        leftHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        rightHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {

        if (leftHeap.isEmpty() || num <= leftHeap.peek()) {
            leftHeap.offer(num);
        } else {
            rightHeap.offer(num);
        }

        if (leftHeap.size() > rightHeap.size() + 1) {
            rightHeap.offer(leftHeap.poll());
        } else if (rightHeap.size() > leftHeap.size()) {
            leftHeap.offer(rightHeap.poll());
        }
    }

    public double findMedian() {
        if (leftHeap.size() == rightHeap.size()) {
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }
        return leftHeap.peek();
    }
}
