package com.interview.prep.mediums.grind75.week2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * Note that:
 *     The valid operators are '+', '-', '*', and '/'.
 *     Each operand may be an integer or another expression.
 *     The division between two integers always truncates toward zero.
 *     There will not be any division by zero.
 *     The input represents a valid arithmetic expression in a reverse polish notation.
 *     The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Example 2:
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 *
 * Example 3:
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 */
public class EvaluateReverseNotionPolishNotation {

    /**
     * Evaluate the expression using a stack:
     * - Push numbers onto the stack.
     * - On encountering an operator, pop the top two numbers, apply the operator,
     *   and push the result back onto the stack.
     *
     * Time Complexity: O(n), where n is the number of tokens.
     * Space Complexity: O(n), for the stack.
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<String> operands = Set.of("+", "-", "/", "*");

        for (int i = 0; i < tokens.length; i++) {
            String current = tokens[i];
            if (!operands.contains(current)) {
                stack.push(Integer.parseInt(current));
            } else {
                int second = stack.pop();
                int first = stack.pop();
                if (current.equals("+")) {
                    stack.push(first + second);
                } else if (current.equals("-")) {
                    stack.push(first - second);
                } else if (current.equals("*")) {
                    stack.push(first * second);
                } else if (current.equals("/")) {
                    stack.push(first / second);
                }
            }
        }
        return stack.peek();
    }

}
