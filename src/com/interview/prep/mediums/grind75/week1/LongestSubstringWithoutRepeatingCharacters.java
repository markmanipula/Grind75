package com.interview.prep.mediums.grind75.week1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a string s, find the length of the longest
 * substring
 * without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Approach: Use a two-pointer approach with a Set to track unique characters.
     * Expand the window with the `right` pointer. If a duplicate is found, shrink the window
     * from the left by moving the `left` pointer until all characters in the window are unique.
     *
     * Time complexity: O(n), where n is the length of the string. Each character is processed at most twice.
     * Space complexity: O(n), as a Set is used to store unique characters of the substring.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            while (set.contains(current)) {
                set.remove(s.charAt(left++));
            }
            set.add(current);
            max = Math.max(max, set.size());
        }
        return max;
    }

}
