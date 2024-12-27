package com.interview.prep.mediums.grind75.week2;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *     MinStack() initializes the stack object.
 *     void push(int val) pushes the element val onto the stack.
 *     void pop() removes the element on the top of the stack.
 *     int top() gets the top element of the stack.
 *     int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Example 1:
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 */
public class MinStack {

    /**
     * Approach 1:
     * Use a custom StackNode class to store:
     * - The value of the node (val).
     * - The minimum value in the stack at or below this node (min).
     * - A reference to the next node (next).
     *
     * The `min` field in each node allows the stack to maintain the minimum value
     * in constant time. The stack operations (push, pop, top, getMin) are all
     * O(1) because we only manipulate or access the top node.
     *
     * Time complexity: O(1) for all operations (push, pop, top, getMin).
     * Space complexity: O(n), where n is the number of elements in the stack,
     * because each node stores its value, min, and a reference to the next node.
     */

    /**
     * Approach 2:
     * Use two stacks to implement the MinStack.
     * 1. The main stack (`stack`) stores all the elements.
     * 2. The auxiliary stack (`minStack`) keeps track of the minimum value at each point in time.
     *    - When pushing a new element, it is added to `minStack` only if it is smaller than or equal to the current minimum.
     *    - When popping an element, the top of `minStack` is also popped if it matches the popped element.
     *
     * Time complexity:
     * - All operations (push, pop, top, getMin) run in O(1) time.
     *
     * Space complexity:
     * - O(n), where n is the number of elements pushed into the stack, as both `stack` and `minStack` can grow to the size of the input.
     */

    /**
     * Will be implementing approach 1
     */
    static class StackNode {
        int val;
        int min;
        StackNode next;

    }

    StackNode top;

    public MinStack() {
    }

    public void push(int val) {
        if (top == null) {
            top = new StackNode();
            top.val = val;
            top.min = val;
            top.next = null;
            return;
        }

        StackNode node = new StackNode();
        node.min = Math.min(val, top.min);
        node.val = val;
        node.next = top;
        top = node;

    }

    public void pop() {
        if (top != null) {
            top = top.next;
        }
    }

    public int top() {
        if (top != null) {
            return top.val;
        }
        return -1;
    }

    public int getMin() {
        if (top != null) {
            return top.min;
        }
        return -1;
    }
    
}
