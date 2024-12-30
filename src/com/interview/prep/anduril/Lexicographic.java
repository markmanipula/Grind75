package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of words `words` and a string `searchWord`, return a list of lists containing
 * up to three lexicographically smallest words from `words` that start with each prefix of `searchWord`.
 *
 * Input:
 * - words: An array of strings representing the available words.
 * - searchWord: A string representing the word whose prefixes are used for searching.
 *
 * Output:
 * - A list of lists where each list corresponds to a prefix of `searchWord` and contains up to
 *   three words from `words` that start with the prefix, sorted lexicographically.
 *
 * Example 1:
 * Input: words = ["mobile", "mouse", "moneypot", "monitor", "mousepad"], searchWord = "mouse"
 * Output: [
 *   ["mobile", "moneypot", "monitor"],
 *   ["mobile", "moneypot", "monitor"],
 *   ["mouse", "mousepad"],
 *   ["mouse", "mousepad"],
 *   ["mouse", "mousepad"]
 * ]
 *
 * Example 2:
 * Input: words = ["havana"], searchWord = "havana"
 * Output: [
 *   ["havana"],
 *   ["havana"],
 *   ["havana"],
 *   ["havana"],
 *   ["havana"],
 *   ["havana"]
 * ]
 *
 * Example 3:
 * Input: words = ["bags", "baggage", "banner", "box", "cloths"], searchWord = "bags"
 * Output: [
 *   ["baggage", "bags", "banner"],
 *   ["baggage", "bags", "banner"],
 *   ["bags"],
 *   ["bags"]
 * ]
 */
public class Lexicographic {

    /**
     * Approach:
     * - Sort the input array of words lexicographically to ensure that suggestions are returned in order.
     * - For each prefix of the searchWord (from the first character to the entire word), find up to three words from the sorted array
     *   that start with the current prefix.
     * - Add these words to the output list for the current prefix.
     *
     * Time Complexity:
     * - O(n log n) for sorting the array (where n is the number of words).
     * - O(m * n), where m is the length of the searchWord and n is the number of words in the array, for generating suggestions.
     *   - For each prefix of length m, we loop through the n words to find matching suggestions.
     *
     * Space Complexity:
     * - O(n) for sorting the array.
     * - O(m * k), where m is the number of prefixes and k is the maximum size of the suggestion list (3 in this case).
     */
    public List<List<String>> suggestedProducts(String[] words, String searchWord) {

        // Sort the array lexicographically to maintain order in the suggestions.
        Arrays.sort(words);

        // Initialize the output list to store suggestions for each prefix of searchWord.
        List<List<String>> output = new ArrayList<>();

        // Iterate through the prefixes of the searchWord.
        for (int i = 0; i < searchWord.length(); i++) {
            String query = searchWord.substring(0, i + 1); // Current prefix of the searchWord.
            List<String> list = new ArrayList<>();
            // Iterate through the sorted array to find matching words for the current prefix.
            for (int j = 0; j < words.length; j++) {
                // Add words starting with the current prefix to the list, up to a maximum of 3.
                if (words[j].startsWith(query) && list.size() < 3) {
                    list.add(words[j]);
                }
            }
            // Add the list of suggestions for the current prefix to the output.
            output.add(list);
        }
        return output;
    }
}