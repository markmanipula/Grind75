package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * Example 2:
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 * Constraints:
 *     k == lists.length
 *     0 <= k <= 104
 *     0 <= lists[i].length <= 500
 *     -104 <= lists[i][j] <= 104
 *     lists[i] is sorted in ascending order.
 *     The sum of lists[i].length will not exceed 104.
 */
public class MergeKSortedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;

        }
    }

    /**
     * Approach:
     * 1. Traverse through all linked lists in the input array `lists` and collect all their node values in a list.
     * 2. Sort the list of values.
     * 3. Create a new sorted linked list by iterating through the sorted list of values and adding nodes to it sequentially.
     *
     * Time Complexity:
     * - Traversing all nodes: O(N), where N is the total number of nodes across all linked lists.
     * - Sorting the list: O(N log N).
     * - Constructing the new linked list: O(N).
     * Overall: O(N log N).
     *
     * Space Complexity:
     * - Additional list to store all node values: O(N).
     * - Sorting space complexity: O(N) (Java's TimSort is used internally for sorting).
     * - New linked list space: O(N) (as it represents the merged nodes).
     * Total space complexity: O(N).
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) return null;

        List<Integer> list = new ArrayList<>();

        for (ListNode listNode : lists) {
            ListNode current = listNode;
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }

        if (list.isEmpty()) return null;

        Collections.sort(list);
        ListNode head = new ListNode(list.get(0));
        ListNode current = head;
        for (int i = 1; i < list.size(); i++) {
            ListNode newNode = new ListNode(list.get(i));
            current.next = newNode;
            current = current.next;
        }
        return head;
    }

    /**
     * Approach:
     * 1. Use a min-heap (PriorityQueue) to store all node values from the input lists.
     * 2. Iterate through each linked list and add its node values to the heap.
     * 3. Poll values from the heap in sorted order to construct a new sorted linked list.
     *
     * Time Complexity:
     * - Adding all values to the heap: O(N * log N), where N is the total number of nodes across all lists.
     * - Polling values from the heap: O(N * log N).
     * - Constructing the linked list: O(N).
     * Overall: O(N * log N).
     *
     * Space Complexity:
     * - Min-heap to store node values: O(N).
     * - New linked list to store nodes: O(N).
     * Total: O(N).
     */
    public ListNode mergeKListsMoreOptimal(ListNode[] lists) {
        if (lists.length == 0) return null;

        // Min-heap to store all node values
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Add all values from linked lists to the heap
        for (ListNode listNode : lists) {
            while (listNode != null) {
                heap.offer(listNode.val);
                listNode = listNode.next;
            }
        }

        // If the heap is empty, return null
        if (heap.isEmpty()) return null;

        // Construct the new sorted linked list
        ListNode head = new ListNode(heap.poll());
        ListNode current = head;

        while (!heap.isEmpty()) {
            current.next = new ListNode(heap.poll());
            current = current.next;
        }

        return head;
    }

    /**
     * Approach:
     * 1. Use a min-heap (PriorityQueue) to store the current smallest node from each list.
     * 2. Add the head nodes of all non-empty lists to the heap.
     * 3. Extract the smallest node from the heap, add it to the result, and push its next node (if available) into the heap.
     * 4. Repeat until the heap is empty.
     *
     * Time Complexity:
     * - Adding and removing nodes from the heap: O(N * log K),
     *   where N is the total number of nodes across all lists, and K is the number of lists.
     * - Overall: O(N * log K).
     *
     * Space Complexity:
     * - Min-heap to store up to K nodes: O(K).
     * - New linked list to store nodes: O(N).
     * Total: O(K + N).
     */
    public ListNode mergeKListsMostOptimal(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-heap to store the current smallest node from each list
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-empty list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }

        // Dummy node to simplify result list construction
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge lists by processing the heap
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            current.next = smallest;
            current = current.next;

            // Add the next node from the list of the smallest node (if available)
            if (smallest.next != null) {
                heap.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}

