package com.interview.prep.anduril;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands {

    /**
     * Approach:
     * Iterate through the grid. When a cell with value '1' (land) is encountered, increment the count of islands
     * and perform a DFS to visit all connected land cells. Mark visited cells by changing their value to '0'
     * to avoid re-processing. Continue until the entire grid is traversed.
     *
     * Time complexity: O(n * m)
     * - The grid is traversed once, and each cell is visited only once.
     *
     * Space complexity: O(n * m) in the worst case due to the recursion stack in DFS for a completely filled grid.
     */
    public int numIslands(char[][] grid) {

        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    numIslandsHelper(grid, i, j);
                }
            }
        }
        return islands;
    }

    public void numIslandsHelper(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row == grid.length || col == grid[0].length || grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '0';

        numIslandsHelper(grid, row + 1, col);
        numIslandsHelper(grid, row - 1, col);
        numIslandsHelper(grid, row, col + 1);
        numIslandsHelper(grid, row, col - 1);
    }
}
