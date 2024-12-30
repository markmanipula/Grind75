package com.interview.prep.anduril;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that will be initialized with a string array,
 * and then it should answer queries of the shortest distance between two different strings from the array.
 * Implement the WordDistance class:
 *
 *     WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 *     int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 * Example 1:
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 *
 * Explanation
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // return 3
 * wordDistance.shortest("makes", "coding");    // return 1
 *
 * Constraints:
 *     1 <= wordsDict.length <= 3 * 104
 *     1 <= wordsDict[i].length <= 10
 *     wordsDict[i] consists of lowercase English letters.
 *     word1 and word2 are in wordsDict.
 *     word1 != word2
 *     At most 5000 calls will be made to shortest.
 */
public class ShortestWordDistance2 {

    String[] wordsDict;
    Map<String, List<Integer>> map;

    /**
     * Approach:
     * - In the constructor, preprocess the input array by storing the indices of each word in a map.
     *   This allows for quick access to all indices of a word when querying.
     * - In the `shortest` method, use the stored indices of the two words to calculate the minimum distance.
     *   Iterate through both lists using nested loops to find the smallest absolute difference.
     *
     * Time Complexity:
     * - Constructor: O(n), where n is the length of `wordsDict`, to process and store the indices.
     * - shortest: O(k1 * k2), where k1 is the number of indices for `word1` and k2 is the number of indices for `word2`.
     *   This occurs because we compare every index of `word1` with every index of `word2`.
     *
     * Space Complexity:
     * - O(n), where n is the length of `wordsDict`, for storing the indices of each word in the map.
     */

    public ShortestWordDistance2(String[] wordsDict) {
        this.wordsDict = wordsDict;
        map = new HashMap<>();

        for (int i = 0; i < wordsDict.length; i++) {
            String current = wordsDict[i];
            if (!map.containsKey(current)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(current, list);
            } else {
                map.get(current).add(i);
            }
        }

    }

    public int shortest(String word1, String word2) {
        List<Integer> word1List = map.get(word1);
        List<Integer> word2List = map.get(word2);

        int min = Integer.MAX_VALUE;
        for (int int1 : word1List) {
            for (int int2 :word2List) {
                min = Math.min(min, Math.abs(int1 - int2));
            }
        }

        return min;
    }
}

