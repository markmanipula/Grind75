package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the
 * anagrams
 * together. You can return the answer in any order.
 *
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Explanation:
 *     There is no string in strs that can be rearranged to form "bat".
 *     The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 *     The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams {

    /**
     * Approach:
     * - Use a HashMap to group strings by their sorted character representation.
     *   1. For each string, sort its characters to generate a "key."
     *   2. If the key is already present in the map, append the string to the corresponding list.
     *   3. If the key is not present, create a new list with the current string.
     * - The values of the map will represent the groups of anagrams.
     * - Finally, convert the map's values into a list of lists to return the grouped anagrams.
     *
     * Time Complexity:
     * - O(n * k log k):
     *   - n is the number of strings.
     *   - k is the maximum length of a string (for sorting each string).
     *
     * Space Complexity:
     * - O(n * k): Space used to store the strings in the map.
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            String sorted = sortString(word);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>(List.of(word)));
            } else {
                List<String> words = map.get(sorted);
                words.add(word);
                map.put(sorted, words);
            }
        }

        List<List<String>> output = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            output.add(entry.getValue());
        }

        return output;
    }

    private String sortString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
