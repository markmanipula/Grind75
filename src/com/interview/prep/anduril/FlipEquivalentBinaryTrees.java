package com.interview.prep.anduril;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.
 *
 * Example 1:
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 *
 * Example 2:
 * Input: root1 = [], root2 = []
 * Output: true
 *
 * Example 3:
 * Input: root1 = [], root2 = [1]
 * Output: false
 */
public class FlipEquivalentBinaryTrees {

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
     * - Recursively compare the two trees to check if they are flip equivalent.
     * - Base Case:
     *   - If both nodes are null, they are equivalent.
     *   - If one of the nodes is null or their values don't match, they are not equivalent.
     * - Recursive Case:
     *   - Two subtrees are flip equivalent if:
     *     1. The left subtree of root1 is flip equivalent to the left subtree of root2
     *        AND the right subtree of root1 is flip equivalent to the right subtree of root2.
     *     OR
     *     2. The left subtree of root1 is flip equivalent to the right subtree of root2
     *        AND the right subtree of root1 is flip equivalent to the left subtree of root2.
     *
     *  Time Complexity: O(n), where n is the total number of nodes in the trees.
     *  Space Complexity: O(h), where h is the height of the tree (due to recursion stack).
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null && root2 == null;
        }

        if (root1.val != root2.val) {
            return false;
        }

        boolean isSameStructure = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        if (isSameStructure) {
            return true;
        }

        boolean isFlipEquivalent = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        if (isFlipEquivalent) {
            return true;
        }

        return false;
    }
}
