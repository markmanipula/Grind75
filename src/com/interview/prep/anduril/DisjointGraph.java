package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a graph represented as an edge list and a number of nodes, determine if the graph
 * is disjoint (i.e., there is more than one connected component). Implement two methods:
 *
 * 1. Check if removing any edge disconnects the graph.
 * 2. Count connected components to determine if the graph is already disjoint.
 *
 * Input:
 * - edges: A 2D array representing the edges of the graph, where each edge connects two nodes.
 * - nodes (n): The total number of nodes in the graph.
 *
 * Output:
 * - A boolean value indicating if the graph is disjoint.
 *
 * Example 1:
 * Input: edges = [[0, 1], [1, 2], [2, 3]], nodes = 4
 * Output: false
 * Explanation: The graph has one connected component.
 *
 * Example 2:
 * Input: edges = [[0, 1], [2, 3]], nodes = 4
 * Output: true
 * Explanation: The graph has two connected components: {0, 1} and {2, 3}.
 */
public class DisjointGraph {

    /**
     * Approach (isDisjointGraph method with edge removal):
     * - Create an adjacency list to represent the graph.
     * - For each edge in the graph:
     *   1. Temporarily remove the edge.
     *   2. Check if the graph becomes disconnected using a DFS traversal.
     *   3. If the graph is disconnected, return true.
     *   4. Restore the edge.
     * - If no edge disconnects the graph, return false.
     *
     * Time Complexity:
     * - O(E * (V + E)) where E is the number of edges and V is the number of nodes.
     *   - For each edge, we perform a DFS traversal of the graph.
     *
     * Space Complexity:
     * - O(V + E) for the adjacency list representation and visited set.
     */
    public static boolean isDisjointGraph(int[][] edges, int nodes) {

        // Create adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Test removing each edge to check if the graph becomes disconnected
        for (int[] edge : edges) {
            graph.get(edge[0]).remove((Integer) edge[1]);
            graph.get(edge[1]).remove((Integer) edge[0]);

            if (isGraphDisconnected(graph, nodes)) {
                return true; // The graph becomes disconnected
            }

            // Restore the edge
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return false; // No edge causes the graph to disconnect
    }

    /**
     * Helper method to check if the graph is disconnected.
     * Uses a DFS traversal starting from node 0.
     */
    private static boolean isGraphDisconnected(Map<Integer, List<Integer>> graph, int nodes) {
        Set<Integer> visited = new HashSet<>();
        dfs1(graph, visited, 0);
        return visited.size() != nodes; // Check if all nodes are visited
    }

    /**
     * Depth-First Search (DFS) helper method.
     */
    private static void dfs1(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node) {
        if (visited.contains(node)) {
            return; // Node already visited
        }
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            dfs1(graph, visited, neighbor); // Recursively visit neighbors
        }
    }

    /**
     * Approach (isDisjointGraph method to check connected components):
     * - Create an adjacency list for the graph.
     * - Perform a DFS traversal for each unvisited node to count connected components.
     * - If there is more than one connected component, the graph is disjoint.
     *
     * Time Complexity:
     * - O(V + E), where V is the number of nodes and E is the number of edges.
     *
     * Space Complexity:
     * - O(V + E) for the adjacency list and visited set.
     */
    public boolean isDisjointGraph(int n, int[][] edges) {

        // Create adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Use a set to track visited nodes
        Set<Integer> visited = new HashSet<>();
        int components = 0;

        // Count the number of connected components
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                dfs(graph, visited, i);
                components++;
            }
        }
        return components == 1; // Return true if the graph has only one component
    }

    /**
     * DFS helper method to traverse the graph.
     */
    public void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int n) {
        if (visited.contains(n)) return; // Node already visited
        visited.add(n);
        for (int neighbor : graph.get(n)) {
            dfs(graph, visited, neighbor); // Recursively visit neighbors
        }
    }
}