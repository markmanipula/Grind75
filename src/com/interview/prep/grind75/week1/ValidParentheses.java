package com.interview.prep.grind75.week1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Example 4:
 * Input: s = "([])"
 * Output: true
 */
public class ValidParentheses {

    /**
     * Approach: Use a stack data structure to track open brackets.
     * - Iterate through the string:
     *   - If the character is an open bracket ('(', '[', '{'), push it onto the stack.
     *   - If the character is a closing bracket (')', ']', '}'):
     *     - Check if the stack is not empty and the top of the stack matches the corresponding open bracket.
     *     - If it matches, pop the stack; otherwise, return false.
     * - At the end, ensure the stack is empty (all brackets matched).
     *
     * Time Complexity: O(n), where n is the length of the string, as we iterate through each character once.
     * Space Complexity: O(n), in the worst case, if all characters are open brackets.
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pairMap = Map.of(')', '(', '}', '{', ']', '[');

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (pairMap.containsValue(current)) {
                stack.push(current);
            } else if (!stack.isEmpty() && pairMap.get(current) == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}

