package com.zaroum.javacodingproblems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Problem: Valid Parentheses
 * 
 * Given a string s containing just the characters '(' and ')', determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of closing brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * 
 * Examples:
 * - Input: s = "()"
 *   Output: true
 *   Explanation: The parentheses are properly matched.
 * 
 * - Input: s = "((()))"
 *   Output: true
 *   Explanation: All opening parentheses have matching closing parentheses in correct order.
 * 
 * - Input: s = "())"
 *   Output: false
 *   Explanation: There's an extra closing parenthesis.
 * 
 * - Input: s = "(()"
 *   Output: false
 *   Explanation: Missing a closing parenthesis.
 * 
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of parentheses only '()'.
 * 
 * Algorithm:
 * Use a counter (balance) to track the balance of parentheses:
 * 1. For each '(' increment the counter
 * 2. For each ')' decrement the counter
 * 3. If counter goes negative at any point, return false (more closing than opening)
 * 4. At the end, counter should be zero for valid parentheses
 * 
 * Time Complexity: O(n) - Single pass through the string
 * Space Complexity: O(1) - Only using a counter variable
 * 
 * This test class validates the correctness of the implementation across various test cases.
 */
public class ValidParenthesesTest {

    /**
     * Determines if the parentheses in a string are valid using a balance counter approach.
     * 
     * @param input the string containing parentheses to validate
     * @return true if parentheses are valid, false otherwise
     */
    public boolean validParentheses(String input) {
        if(input == null || input.length() == 0)
            return true; // Empty string is considered valid
            
        int balance = 0;
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '(') {
                balance++;
            } else if(c == ')') {
                balance--;
                // If we have more closing than opening at any point, it's invalid
                if(balance < 0) {
                    return false;
                }
            }
        }
        
        // Valid only if all opening parentheses are matched
        return balance == 0;
    }

    /**
     * Parameterized test for validParentheses method
     * Tests various input strings and verifies if parentheses are valid
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testValidParentheses(String input, boolean expectedValid) {
        // Given & When
        boolean actualValid = validParentheses(input);
        
        // Then
        assertThat(actualValid)
            .as("Parentheses validation for input: '%s'", input)
            .isEqualTo(expectedValid);
    }

    /**
     * Additional test method to verify performance with larger inputs
     */
    @ParameterizedTest
    @MethodSource("providePerformanceTestCases")
    void testValidParenthesesPerformance(String input, boolean expectedValid) {
        // Given & When
        long startTime = System.nanoTime();
        boolean actualValid = validParentheses(input);
        long endTime = System.nanoTime();
        
        // Then
        assertThat(actualValid)
            .as("Performance test - Parentheses validation for input length: %d", input.length())
            .isEqualTo(expectedValid);
            
        // Verify reasonable performance (should complete quickly for O(n) algorithm)
        long executionTime = endTime - startTime;
        assertThat(executionTime)
            .as("Execution time should be reasonable for input of length %d", input.length())
            .isLessThan(5_000_000L); // 5ms in nanoseconds
    }

    /**
     * Provides test data for the parameterized test
     * @return Stream of Arguments containing input string and expected validity
     */
    static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Edge cases
            Arguments.of(null, true),     // Null input
            Arguments.of("", true),       // Empty string
            
            // Basic valid cases
            Arguments.of("()", true),     // Simple pair
            Arguments.of("(())", true),   // Nested
            Arguments.of("()()", true),   // Multiples pairs
            Arguments.of("((()))", true), // Multiple nested
            Arguments.of("(()())", true), // Mixed nested and sequential
            Arguments.of("(()(()))", true), // Complex nesting
            
            // Basic invalid cases
            Arguments.of("(", false),     // Single opening
            Arguments.of(")", false),     // Single closing
            Arguments.of("((", false),    // Multiple opening
            Arguments.of("))", false),    // Multiple closing
            Arguments.of("())", false),   // Extra closing
            Arguments.of("(()", false),   // Missing closing
            Arguments.of(")(", false),    // Wrong order
            Arguments.of("())(", false),  // Mixed wrong order
            
            // Complex invalid cases
            Arguments.of("((())", false), // Unbalanced nested
            Arguments.of("(())))", false), // Too many closing
            Arguments.of("(((()", false), // Too many opening
            Arguments.of(")()(", false),  // Starts with closing
            Arguments.of("()(()", false), // Ends unbalanced
            
            // Longer sequences
            Arguments.of("(()()())", true),    // Long valid
            Arguments.of("((())()())", true),  // Long valid nested
            Arguments.of("(()()())()", true),  // Multiple groups
            Arguments.of("(()()()))", false),  // Long invalid
            Arguments.of("((())()())(", false), // Long invalid ending
            
            // Edge patterns
            Arguments.of("()()()()", true),    // All sequential
            Arguments.of("(((())))", true),    // All nested
            Arguments.of("()()()())", false),  // Almost valid
            Arguments.of("(()()()())", true)  //  valid nested
        );
    }

    /**
     * Provides performance test cases with larger inputs
     */
    static Stream<Arguments> providePerformanceTestCases() {
        // Generate large valid parentheses strings
        StringBuilder validLarge = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            validLarge.append("()");
        }
        
        StringBuilder validNested = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            validNested.append("(");
        }
        for (int i = 0; i < 1000; i++) {
            validNested.append(")");
        }
        
        // Generate large invalid parentheses strings
        StringBuilder invalidLarge = new StringBuilder();
        for (int i = 0; i < 999; i++) {
            invalidLarge.append("()");
        }
        invalidLarge.append("("); // Missing closing
        
        return Stream.of(
            Arguments.of(validLarge.toString(), true),   // 2000 chars, all valid pairs
            Arguments.of(validNested.toString(), true),  // 2000 chars, deeply nested
            Arguments.of(invalidLarge.toString(), false), // 1999 chars, missing one closing
            Arguments.of("(".repeat(5000), false),       // 5000 opening parentheses
            Arguments.of(")".repeat(5000), false)        // 5000 closing parentheses
        );
    }
}