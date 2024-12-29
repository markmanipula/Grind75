package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class CourseSchedule {

    /**
     * Approach:
     * 1. We first build an adjacency list representing the graph where each course
     *    has a list of prerequisites.
     * 2. We use Depth-First Search (DFS) to detect cycles in the graph. If a cycle is found,
     *    it means that it's not possible to complete all courses (i.e., we cannot finish the courses).
     * 3. If no cycle is detected, then it is possible to finish all courses.
     *
     * Time complexity: O(n + m), where n is the number of courses (vertices) and m is the number of prerequisites (edges).
     * Space complexity: O(n + m), for the adjacency list and the recursion stack.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //create adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] array : prerequisites) {
            int course = array[0];
            int prerequisite = array[1];
            graph.get(course).add(prerequisite);
        }

        Set<Integer> visited = new HashSet<>();
        //helps identify if a course being visited is already part of the current
        // DFS traversal path, which is an indicator of a cycle.
        Set<Integer> recursionStack = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                if (dfs(graph, visited, recursionStack, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> recursionStack, int course) {

        // If the course is already in the current DFS path (recursion stack), a cycle is detected
        if (recursionStack.contains(course)) {
            return true;
        }

        // If the course is fully processed and no cycles were found, skip further processing
        if (visited.contains(course)) {
            return false;
        }

        visited.add(course);
        recursionStack.add(course);

        for (int neighbor : graph.get(course)) {
            if (dfs(graph, visited, recursionStack, neighbor)) {
                return true;
            }
        }

        // Remove the course from the recursion stack since we are backtracking
        recursionStack.remove(course);
        return false;
    }
}
