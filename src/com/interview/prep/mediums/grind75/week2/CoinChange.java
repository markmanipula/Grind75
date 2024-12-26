package com.interview.prep.mediums.grind75.week2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChange {

    /**
     * Approach:
     * Use Breadth-First Search (BFS) to find the minimum number of coins needed to make up a given amount.
     * Each node represents an amount of money formed by using some coins, starting from 0.
     *
     * BFS explores all possible sums incrementally by adding available coins, ensuring that the first time we
     * reach the target amount, it uses the minimum number of coins.
     *
     * Steps:
     * 1. Initialize a queue to track current sums, starting with 0, and a set to track visited sums.
     * 2. For each level of the BFS, increment the coin count and process all sums reachable from the current
     *    sum using available coins.
     * 3. If a sum equals the target amount, return the coin count.
     * 4. If the queue is exhausted without finding the target amount, return -1.
     *
     * Time Complexity: O(n * m), where n is the target amount and m is the number of coin denominations.
     * Space Complexity: O(n), for the queue and the visited set.
     */
    public int coinChangeBfs(int[] coins, int amount) {
        if (amount == 0) return 0;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        //add initial amount to queue
        queue.offer(0);
        visited.add(0);

        int numCoins = 0;

        //bfs
        while (!queue.isEmpty()) {
            numCoins++;
            int level = queue.size();

            for (int i = 0; i < level; i++) {
                int current = queue.poll();

                for (int coin : coins) {
                    int next = current + coin;

                    if (next == amount) {
                        return numCoins;
                    }

                    if (next < amount && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

        }
        return -1;
    }

    /**
     * Approach:
     * Use a recursive depth-first search (DFS) with memoization to find the minimum number of coins needed
     * to make up a given amount. For each recursive call, explore using each type of coin in the `coins` array.
     *
     * Memoization caches results for each unique amount, preventing redundant calculations and improving
     * efficiency for overlapping subproblems.
     *
     * Steps:
     * 1. Base case: If the amount is zero, return 0 (solution found). If the amount is negative, return
     *    Integer.MAX_VALUE (invalid path).
     * 2. If the result for the current amount is already in the memo, return it.
     * 3. For each coin, recursively calculate the minimum coins needed for the remaining amount (`amount - coin`).
     *    Update the result with the smallest value.
     * 4. Cache the result for the current amount and return it.
     *
     * Time Complexity: O(n * m), where n is the target amount and m is the number of coin denominations.
     * Space Complexity: O(n), for the memoization map storing results for amounts from 0 to the target amount.
     */
    public int coinChangeDfs(int[] coins, int amount) {

        //this will be the cache for the remaining amount and the coins counted so far
        Map<Integer, Integer> memo = new HashMap<>();

        int result = dfs(coins, memo, amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dfs(int[] coins, Map<Integer, Integer> memo, int amount) {
        //return an invalid amount so the main method can return -1
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        //an exact amount is found so retuning 0 so the recursive call can add 1 to it
        if (amount == 0) {
            return 0;
        }

        if (memo.containsKey(amount)) return memo.get(amount);

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = dfs(coins, memo, amount - coin);
            if (result != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        memo.put(amount, minCoins);
        return minCoins;
    }

}
