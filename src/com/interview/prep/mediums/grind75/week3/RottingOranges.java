package com.interview.prep.mediums.grind75.week3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *     0 representing an empty cell,
 *     1 representing a fresh orange, or
 *     2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],
 *                [1,1,0],
 *                [0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],
 *                [0,1,1],
 *                [1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
public class RottingOranges {

    /**
     * Approach: bfs. use queue and the store the coordinates for rotten oranges
     *   Key points:
     *      Keep track of the number of fresh oranges. If all fresh oranges are processed during BFS,
     *      return the time taken.
     *      If there are still fresh oranges left after BFS, return -1 (some oranges cannot be rotted).
     *
     * Time complexity: o(n x m) where n is the row count and m is the column count
     * Space complexity: o(n x m) in worst case if all items in the matrix is 'rotten'
     */
    public int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        int time = 0;

        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty() && freshOranges > 0) {

            time++;
            int rottenCounts = queue.size();

            for (int i = 0; i < rottenCounts; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

        }
        return freshOranges == 0 ? time : -1;

    }
}
