package com.interview.prep.anduril;

import java.util.Stack;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it,
 * and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions,
 * such as eval().
 *
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
public class BasicCalculator {

    /**
     * Approach:
     * 1. Iterate through the string `s` character by character:
     *    - If the character is a digit, build the current number.
     *    - If the character is '+' or '-', update the result with the current number and update the sign.
     *    - If the character is '(', push the current result and sign onto the stack to save the context.
     *      Reset the `result` and `sign` for the new sub-expression inside the parentheses.
     *    - If the character is ')', calculate the result for the sub-expression,
     *      apply the sign before the parentheses, and add it to the previous context.
     * 2. After iterating, handle any remaining number by adding it to the result.
     *
     * Time Complexity:
     * - The algorithm processes each character of the input string once: O(n), where `n` is the length of the string.
     * - Each stack operation (push and pop) takes O(1) and is performed a limited number of times (depending on the parentheses).
     * Overall: O(n).
     *
     * Space Complexity:
     * - The stack is used to store intermediate results and signs for parentheses.
     * - In the worst case (e.g., deeply nested parentheses), the stack can grow to O(n).
     * Total: O(n).
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>(); // To store intermediate results and signs
        int result = 0; // Final result of the expression
        int number = 0; // Current number being parsed
        int sign = 1; // Current sign, 1 for positive, -1 for negative

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // Build the current number
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                // Add the previous number with the current sign to the result
                result += sign * number;
                number = 0; // Reset the number
                sign = 1; // Update sign to positive
            } else if (c == '-') {
                // Subtract the previous number with the current sign
                result += sign * number;
                number = 0; // Reset the number
                sign = -1; // Update sign to negative
            } else if (c == '(') {
                // Save the current result and sign before the parentheses
                stack.push(result);
                stack.push(sign);
                // Reset result and sign for the new context
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Add the current number with the current sign to the result
                result += sign * number;
                number = 0; // Reset the number
                // Multiply the result inside parentheses with the saved sign
                result *= stack.pop();
                // Add the result before the parentheses
                result += stack.pop();
            }
        }

        // Add the last number (if any) to the result
        if (number != 0) {
            result += sign * number;
        }

        return result;
    }
}
