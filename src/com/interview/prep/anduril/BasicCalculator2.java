package com.interview.prep.anduril;

import java.util.Stack;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions,
 * such as eval().
 *
 * Example 1:
 * Input: s = "3+2*2"
 * Output: 7
 *
 * Example 2:
 * Input: s = " 3/2 "
 * Output: 1
 *
 * Example 3:
 * Input: s = " 3+5 / 2 "
 * Output: 5
 */
public class BasicCalculator2 {

    /**
     * Approach:
     * 1. Use a stack to handle operator precedence.
     * 2. Iterate through the string `s`, parsing numbers and operators.
     * 3. Push results for multiplication and division immediately to the stack, while addition and subtraction are deferred.
     * 4. At the end, sum up all values in the stack.
     *
     * Time Complexity: O(n), where `n` is the length of the string.
     * Space Complexity: O(n), due to the stack.
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int number = 0; // Current number being parsed
        char operator = '+'; // Initial operator is '+'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character is a digit, construct the current number
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }

            // If the character is an operator or the last character in the string
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (operator == '+') {
                    stack.push(number);
                } else if (operator == '-') {
                    stack.push(-number);
                } else if (operator == '*') {
                    stack.push(stack.pop() * number);
                } else if (operator == '/') {
                    stack.push(stack.pop() / number);
                }

                // Update the operator and reset the number
                operator = c;
                number = 0;
            }
        }

        // Sum up all values in the stack to get the result
        int result = 0;
        for (int num : stack) {
            result += num;
        }

        return result;
    }
}
