package com.interview.prep.anduril;

import java.util.HashSet;
import java.util.Set;

public class ShortestWordDistance {

    /**
     * Approach:
     * 1. Traverse the array while keeping track of the latest indices of word1 and word2.
     * 2. Each time one of the words is found:
     *    - Update the corresponding index.
     *    - If both indices are valid, calculate the distance and update the minimum.
     * 3. Return the minimum distance found.
     *
     * Time Complexity: O(n)
     * - The algorithm performs a single pass through the array (n is the size of `wordsDict`).
     *
     * Space Complexity: O(1)
     * - Only a few integer variables are used.
     */
    public int shortestDistanceOptimal(String[] wordsDict, String word1, String word2) {

        int index1 = -1;
        int index2 = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                index1 = i;
            } else if (wordsDict[i].equals(word2)) {
                index2 = i;
            }

            if (index1 != -1 && index2 != -1) {
                min = Math.min(min, Math.abs(index1 - index2));
            }
        }
        return min;
    }

    /**
     * Approach:
     * 1. Use a `HashSet` to store the target words (word1 and word2) for quick membership checks.
     * 2. Traverse the array to find the first occurrence of either target word and initialize pointers.
     * 3. Continue traversing the array:
     *    - Update the minimum distance when encountering a different target word.
     *    - Update the pointer for the current word if encountered again.
     * 4. Return the minimum distance found.
     *
     * Time Complexity: O(n)
     * - The algorithm performs a single pass through the array (n is the size of `wordsDict`).
     *
     * Space Complexity: O(1)
     * - Only a HashSet of constant size (2 words) is used.
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        // Use a HashSet to store the target words for quick membership checks
        Set<String> set = new HashSet<>();
        set.add(word1);
        set.add(word2);

        int min = Integer.MAX_VALUE; // Initialize minimum distance to maximum value
        int left = 0; // Pointer to track the previous occurrence of a target word
        String current = ""; // Keeps track of the most recently encountered target word

        // Find the first occurrence of either word1 or word2
        for (int i = 0; i < wordsDict.length; i++) {
            if (set.contains(wordsDict[i])) {
                current = wordsDict[i];
                left = i;
                break; // Exit the loop once the first target word is found
            }
        }

        // Iterate through the array starting from the position after the first occurrence
        for (int right = left + 1; right < wordsDict.length; right++) {
            // If another target word is found and it's different from the current one
            if (set.contains(wordsDict[right]) && !wordsDict[right].equals(current)) {
                min = Math.min(min, right - left); // Update minimum distance
                current = wordsDict[right]; // Update current word
            }

            // If the same word is encountered, update the left pointer to the new position
            if (set.contains(wordsDict[right]) && wordsDict[right].equals(current)) {
                left = right;
            }
        }

        return min; // Return the minimum distance
    }
}
