package com.interview.prep.mediums.grind75.week2;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *     The left
 *     subtree
 *     of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 *
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

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
     * Approach:
     * Use DFS to validate the BST property.
     * Maintain a range (min, max) for each node to ensure that:
     * - All values in the left subtree are less than the current node's value.
     * - All values in the right subtree are greater than the current node's value.
     *
     * Time complexity: O(n), where n is the number of nodes in the tree.
     * Space complexity: O(n), for the recursion stack in the worst case (skewed tree).
     *
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //using long to not limit to the integer min/max value
    private boolean isValidBSTHelper(TreeNode node, long left, long right) {
        if (node == null) return true;

        if (node.val <= left || node.val >= right) {
            return false;
        }

        //go to left child and use the current node to compare it and make sure it less than it
        boolean leftTree = isValidBSTHelper(node.left, left, node.val);
        //go to right child and use the current node to compare it and make sure it greater than it
        boolean rightTree = isValidBSTHelper(node.right, node.val, right);

        return leftTree && rightTree;
    }
}
