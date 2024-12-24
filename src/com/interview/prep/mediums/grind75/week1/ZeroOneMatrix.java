package com.interview.prep.mediums.grind75.week1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 */
public class ZeroOneMatrix {

    /**
     * Approach:
     * 1. Initialize an output matrix (`distance`) to store the minimum distances:
     *    - Set cells with `0` in the input matrix to `0` in `distance`, and add their coordinates to the queue.
     *    - Set cells with `1` in the input matrix to `Integer.MAX_VALUE` in `distance` to represent unprocessed cells.
     * 2. Use Breadth-First Search (BFS) to process the queue:
     *    - For each cell dequeued, iterate through its neighbors (up, down, left, right).
     *    - If a neighbor has a distance greater than the current cell's distance + 1:
     *        - Update the neighbor's distance to `current distance + 1`.
     *        - Add the neighbor to the queue for further processing.
     *
     * Time complexity: O(n * m), where `n` is the number of rows and `m` is the number of columns.
     * Each cell is processed once, and each edge is visited at most once.
     *
     * Space complexity: O(n * m) in the worst case, where the queue and `distance` matrix both store all cells.
     */
    public int[][] updateMatrix(int[][] mat) {
        int[][] distance = new int[mat.length][mat[0].length];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0;
                    queue.offer(new int[] {i, j});
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < mat.length && newCol >= 0 && newCol < mat[0].length) {
                    //this means that the neighbor has the unprocessed '1' value
                    // the +1 here indicates the default adjacent distance
                    if (distance[newRow][newCol] > distance[row][col] + 1) {
                        distance[newRow][newCol] = distance[row][col] + 1;
                        queue.offer(new int[] {newRow, newCol});
                    }
                }
            }
        }

        return distance;
    }
}
