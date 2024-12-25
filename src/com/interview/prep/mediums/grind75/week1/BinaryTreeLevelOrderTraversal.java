package com.interview.prep.mediums.grind75.week1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 */
public class BinaryTreeLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Approach: Perform a Breadth-First Search (BFS) using a queue.
     * - Start by adding the root node to the queue.
     * - Process all nodes at the current level by iterating `queue.size()` times.
     * - For each node, add its value to the current level's list and enqueue its children (if any).
     * - Add the completed level's list to the output.
     *
     * Time complexity: O(n), where n is the number of nodes in the tree (each node is processed once).
     * Space complexity: O(n), for the queue that stores up to `n` nodes in the worst case (last level of the tree).
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> output = new ArrayList<>();
        if (root == null) return output;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            List<Integer> list = new ArrayList<>();
            // Number of nodes at the current level.
            int size = queue.size();
            // Process all nodes at the current level.
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                list.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            output.add(list);
        }

        return output;
    }
}
