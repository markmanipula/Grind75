package com.interview.prep.anduril;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting
 * from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
 *
 * You start on square 1 of the board. In each move, starting from square curr, do the following:
 *
 *     Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
 *         This choice simulates the result of a standard 6-sided die roll: i.e.,
 *         there are always at most 6 destinations, regardless of the size of the board.
 *     If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
 *     The game ends when you reach the square n2.
 *
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c]. Squares 1 and n2 are not the starting points of any snake or ladder.
 *
 * Note that you only take a snake or ladder at most once per dice roll.
 * If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
 * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2.
 * You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 *
 * Return the least number of dice rolls required to reach the square n2. If it is not possible to reach the square, return -1.
 *
 * Example 1:
 * Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * In the beginning, you start at square 1 (at row 5, column 0).
 * You decide to move to square 2 and must take the ladder to square 15.
 * You then decide to move to square 17 and must take the snake to square 13.
 * You then decide to move to square 14 and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * This is the lowest possible number of moves to reach the last square, so return 4.
 *
 * Example 2:
 * Input: board = [[-1,-1],[-1,3]]
 * Output: 1
 */
public class SnakesAndLadders {

    /**
     * Approach:
     * 1. Flatten the 2D board into a 1D array while maintaining the boustrophedon order.
     * 2. Use Breadth-First Search (BFS) to simulate the game:
     *    - Start at position 1.
     *    - For each position, explore up to 6 moves ahead (simulating dice rolls).
     *    - If a snake or ladder is encountered, move to the destination.
     *    - Track visited positions to avoid redundant work.
     *    - Return the number of moves when the last square is reached.
     * 3. If the last square is unreachable, return -1.
     *
     * Time Complexity:
     * O(n^2) - BFS visits each cell of the board once, and each cell has up to 6 neighbors.
     *
     * Space Complexity:
     * O(n^2) - Storage required for the flattened board, BFS queue, and visited set.
     */
    public int snakesAndLadders(int[][] board) {
        int length = board.length;
        int goal = length * length;

        // Flatten the board into a 1D array
        int[] array = new int[goal + 1];
        int index = 1;
        boolean toRight = true;

        for (int i = length - 1; i >= 0; i--) {
            if (toRight) {
                for (int j = 0; j < length; j++) {
                    array[index++] = board[i][j];
                }
            } else {
                for (int j = length - 1; j >= 0; j--) {
                    array[index++] = board[i][j];
                }
            }
            toRight = !toRight;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(1);
        visited.add(1);

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == goal) {
                    return moves;
                }

                for (int j = 1; j <= 6; j++) { // Dice rolls range from 1 to 6
                    int next = current + j;

                    if (next > goal) break; // Ensure no out-of-bounds access

                    if (array[next] != -1) {
                        next = array[next]; // Move to destination if snake/ladder exists
                    }

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            moves++;
        }

        return -1; // If the goal is not reachable
    }
    

}
