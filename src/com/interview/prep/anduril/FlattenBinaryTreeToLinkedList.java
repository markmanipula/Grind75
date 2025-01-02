package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 *     The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 *     The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 */
public class FlattenBinaryTreeToLinkedList {

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
     * Approach: Use DFS to perform a pre-order traversal and store the values of the nodes
     * in an ArrayList. Then, use the list to reconstruct the tree as a flattened "linked list".
     *
     * Time complexity: O(n), where n is the number of nodes, since we visit each node once during DFS and again while reconstructing the tree.
     * Space complexity: O(n) - O(n) for the ArrayList to store node values, and O(h) for the recursion stack,
     * where h is the height of the tree. In the worst case (skewed tree), h can be O(n), resulting in total O(n).
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        root.left = null;
        TreeNode runner = root;
        for (int i = 1; i < list.size(); i++) {
            TreeNode newNode = new TreeNode(list.get(i));
            runner.right = newNode;
            runner = runner.right;
        }
    }

    private void dfs(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) return;

        list.add(treeNode.val);
        dfs(treeNode.left, list);
        dfs(treeNode.right, list);
    }

    /**
     * Approach:
     * - The method uses a Morris Traversal-like approach to flatten the tree in-place.
     * - For each node:
     *   1. If the node has a left child:
     *      a. Find the rightmost node of the left subtree (predecessor).
     *      b. Connect the predecessor's `right` pointer to the node's `right` child.
     *      c. Move the left subtree to the right and nullify the left child.
     *   2. Move to the next node (current.right).
     *
     * Time complexity: O(n), where n is the number of nodes in the tree.
     * - Each node is visited once.
     * - The inner while loop to find the rightmost node is executed at most once for each node.
     *
     * Space complexity: O(1)
     * - The algorithm performs the flattening in-place without using recursion or additional data structures.
     */
    public void flattenOptional(TreeNode root) {
        TreeNode current = root;
        TreeNode prev = null;

        while (current != null) {
            if (current.left != null) {
                prev = current.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

}
