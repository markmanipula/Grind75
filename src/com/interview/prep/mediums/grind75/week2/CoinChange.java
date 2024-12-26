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
     * We explore all possible sums that can be formed incrementally by adding available coins,
     * visiting each node level by level (or layer by layer) until we reach the target amount.
     *
     * Steps:
     * 1. Initialize a queue to keep track of the current sums reached, starting with 0.
     * 2. Use a set to track visited sums to avoid processing the same amount multiple times.
     * 3. For each level of the BFS, increment the coin count and process all states reachable
     *    from the current sums using the available coins.
     * 4. If we reach the target amount, return the count of coins used.
     * 5. If the queue is exhausted without finding the target, return -1 indicating that it is not possible.
     *
     * Time complexity: O(n * m), where n is the target amount and m is the number of coin denominations.
     * This is because we may visit all amounts from 0 to `amount` and for each amount, we explore all `m` coins.
     *
     * Space complexity: O(n) for the storage of the queue and the set of visited amounts, since we may store
     * at most `amount` unique sums.
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
     * Approach: Use a recursive depth-first search (DFS) with memoization to find the minimum number of coins needed
     * to make up a given amount. For each call, we explore the possibility of using each type of coin in the coins array.
     * We continue recursively until the amount becomes zero (solution found) or negative (invalid path).
     *
     * The memoization technique caches results for each unique amount, preventing redundant calculations and improving
     * efficiency for overlapping subproblems.
     *
     * Time complexity: O(n * m), where n is the target amount and m is the number of coin denominations.
     * This is because we explore up to n different amounts and for each amount, we potentially check m coins.
     *
     * Space complexity: O(n) for the memoization cache that stores results for amounts from 0 to the target amount.
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
