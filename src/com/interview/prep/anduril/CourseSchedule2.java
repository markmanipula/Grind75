package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseSchedule2 {

    /**
     * Approach:
     * 1. Create an adjacency list representation of the graph, where each course points to the courses that depend on it.
     * 2. Maintain an array `dependencies` to track the number of prerequisites for each course.
     * 3. Add all courses with no prerequisites (dependencies[i] == 0) to a queue for processing.
     * 4. Use BFS to process courses:
     *    - Dequeue a course, add it to the result, and decrement the dependencies of its neighbors.
     *    - If a neighbor's dependencies become zero, add it to the queue.
     * 5. If all courses are processed, return the result; otherwise, return an empty array (indicating a cycle).
     *
     * Why does this work?
     * - The BFS approach ensures that we process courses in a topological order:
     *   - Courses with no prerequisites are processed first.
     *   - Dependent courses are processed only when their prerequisites are satisfied.
     * - By using the `dependencies` array, we detect cycles if not all courses are processed.
     *
     * Time Complexity:
     * - Constructing the graph takes O(V + E), where V is the number of courses (nodes)
     *   and E is the number of prerequisites (edges).
     * - BFS processes each node and edge once, so it also takes O(V + E).
     * - Overall: O(V + E).
     *
     * Space Complexity:
     * - The adjacency list requires O(V + E).
     * - The queue and the dependencies array each require O(V).
     * - Overall: O(V + E).
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create adjacency list and dependencies array
        int[] dependencies = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] item : prerequisites) {
            int course = item[0];
            int prereq = item[1];
            dependencies[course]++;
            graph.get(prereq).add(course);
        }

        // Add nodes with no dependencies to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < dependencies.length; i++) {
            if (dependencies[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform BFS
        int[] result = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;

            for (int neighbor : graph.get(current)) {
                dependencies[neighbor]--;
                if (dependencies[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if we processed all courses
        return index == numCourses ? result : new int[0];
    }
}
