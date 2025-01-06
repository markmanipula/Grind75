package com.interview.prep.anduril;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life,
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state:
 * live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 *
 *     Any live cell with fewer than two live neighbors dies as if caused by under-population.
 *     Any live cell with two or three live neighbors lives on to the next generation.
 *     Any live cell with more than three live neighbors dies, as if by over-population.
 *     Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * The next state of the board is determined by applying the above rules simultaneously to
 * every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
 *
 * Given the current state of the board, update the board to reflect its next state.
 *
 * Note that you do not need to return anything.
 *
 * Example 1:
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * Example 2:
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 */
public class GameOfLife {

    /**
     * Approach:
     * 1. Use a directions array to represent the 8 possible neighbors of a cell.
     * 2. In the first pass:
     *    - For each cell, count live neighbors.
     *    - Mark cells transitioning from `1 -> 0` as `2` and `0 -> 1` as `-1`.
     * 3. In the second pass:
     *    - Finalize the board by converting `2` to `0` and `-1` to `1`.
     *
     * Time Complexity: O(m * n), where m is the number of rows and n is the number of columns.
     * Space Complexity: O(1), as updates are made in-place.
     */
    class Solution {
        public void gameOfLife(int[][] board) {
            int[][] directions = {
                    {0, 1}, {0, -1}, {1, 0}, {-1, 0},
                    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
            };

            int rows = board.length;
            int cols = board[0].length;

            // Step 1: Apply transitions using temporary states
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int liveNeighbors = 0;

                    for (int[] direction : directions) {
                        int newRow = i + direction[0];
                        int newCol = j + direction[1];

                        // Check bounds and count live neighbors (consider temporary state 2)
                        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                                (board[newRow][newCol] == 1 || board[newRow][newCol] == 2)) {
                            liveNeighbors++;
                        }
                    }

                    // Apply rules for live and dead cells
                    if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                        board[i][j] = 2;  // Mark as `1 -> 0`
                    } else if (board[i][j] == 0 && liveNeighbors == 3) {
                        board[i][j] = -1; // Mark as `0 -> 1`
                    }
                }
            }

            // Step 2: Finalize the board
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 2) {
                        board[i][j] = 0;  // Convert `1 -> 0`
                    } else if (board[i][j] == -1) {
                        board[i][j] = 1;  // Convert `0 -> 1`
                    }
                }
            }
        }
    }
}
