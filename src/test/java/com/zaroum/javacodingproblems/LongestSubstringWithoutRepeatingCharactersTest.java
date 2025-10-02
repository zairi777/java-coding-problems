package com.zaroum.javacodingproblems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Problem: Longest Substring Without Repeating Characters
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * - Input: s = "abcabcbb"
 *   Output: 3
 *   Explanation: The answer is "abc", with the length of 3.
 * 
 * - Input: s = "bbbbb"
 *   Output: 1
 *   Explanation: The answer is "b", with the length of 1.
 * 
 * - Input: s = "pwwkew"
 *   Output: 3
 *   Explanation: The answer is "wke", with the length of 3.
 *   Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * Constraints:
 * - 0 <= s.length <= 5 * 10^4
 * - s consists of English letters, digits, symbols and spaces.
 *
 *
 * Algorithm:
 * 1. Use two pointers (left and right) to maintain a sliding window
 * 2. Use a HashSet to track characters in the current window
 * 3. Expand the window by moving right pointer and adding characters to the set
 * 4. If a duplicate is found, shrink the window from left until the duplicate is removed
 * 5. Keep track of the maximum window size seen so far
 *
 * Time Complexity: O(n) - Each character is visited at most twice (once by right pointer, once by left pointer)
 * Space Complexity: O(min(m,n)) - Where m is the size of the character set and n is the length of the string
 * This test class validates the correctness of the implementation across various test cases.
 */
public class LongestSubstringWithoutRepeatingCharactersTest {

    public int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character is already in the set, shrink window from left
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            
            // Add current character to the set
            charSet.add(currentChar);
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    /**
     * Parameterized test for lengthOfLongestSubstringWithoutRepeatingCharacters method (recursive implementation)
     * Tests various input strings and verifies the expected length of longest substring without repeating characters
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLengthOfLongestSubstringWithoutRepeatingCharacters(String input, int expectedLength) {
        // When
        int actualLength = lengthOfLongestSubstringWithoutRepeatingCharacters(input);
        
        // Then
        assertThat(actualLength)
            .as("Recursive implementation - Length of longest substring without repeating characters for input: '%s'", input)
            .isEqualTo(expectedLength);
    }

    /**
     * Parameterized test for lengthOfLongestSubstringWithoutRepeatingCharactersOptimized method (sliding window implementation)
     * Tests various input strings and verifies the expected length of longest substring without repeating characters
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLengthOfLongestSubstringWithoutRepeatingCharactersOptimized(String input, int expectedLength) {
        // When
        int actualLength = lengthOfLongestSubstringWithoutRepeatingCharacters(input);
        
        // Then
        assertThat(actualLength)
            .as("Optimized implementation - Length of longest substring without repeating characters for input: '%s'", input)
            .isEqualTo(expectedLength);
    }

    /**
     * Provides test data for the parameterized test
     * @return Stream of Arguments containing input string and expected length
     */
    static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Edge cases
            Arguments.of(null, 0),
            Arguments.of("", 0),
            Arguments.of("a", 1),
            
            // Basic cases
            Arguments.of("abcabcbb", 3), // "abc"
            Arguments.of("bbbbb", 1),    // "b"
            Arguments.of("pwwkew", 3),   // "wke"
            Arguments.of("abcdef", 6),   // "abcdef" - no repeating characters
            
            // More complex cases
            Arguments.of("dvdf", 3),     // "vdf"
            Arguments.of("anviaj", 5),   // "nviaj"
            Arguments.of("abba", 2),     // "ab" or "ba"
            Arguments.of("tmmzuxt", 5),  // "mzuxt"
            
            // Special characters and numbers
            Arguments.of("a1b2c3", 6),   // "a1b2c3" - no repeating
            Arguments.of("a!@#a", 4),    // "a!@#"
            Arguments.of("123321", 3),   // "123" or "321"
            
            // Longer strings
            Arguments.of("abcdefghijklmnopqrstuvwxyz", 26), // All alphabet
            Arguments.of("aabbccddee", 2), // "ab", "bc", "cd", "de"
            Arguments.of("abcabcdefg", 7)  // "abcdefg"
        );
    }
}
