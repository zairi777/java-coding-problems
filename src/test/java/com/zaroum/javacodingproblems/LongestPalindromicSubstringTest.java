package com.zaroum.javacodingproblems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Problem: Longest Palindromic Substring
 * 
 * Given a string s, return the longest palindromic substring in s.
 * A palindrome is a string that reads the same forward and backward.
 * 
 * Examples:
 * - Input: s = "babad"
 *   Output: "bab" or "aba" (both are valid answers)
 *   Explanation: Both "bab" and "aba" are palindromes of length 3.
 * 
 * - Input: s = "cbbd"
 *   Output: "bb"
 *   Explanation: The longest palindromic substring is "bb" with length 2.
 * 
 * - Input: s = "racecar"
 *   Output: "racecar"
 *   Explanation: The entire string is a palindrome.
 * 
 * - Input: s = "ac"
 *   Output: "a" or "c"
 *   Explanation: Both single characters are palindromes of length 1.
 * 
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consist of only digits and English letters.
 * 
 * Algorithm: Expand Around Centers
 * The idea is to consider each character (and each pair of characters for even-length palindromes) 
 * as potential centers and expand outward while characters match.
 * 
 * Steps:
 * 1. For each possible center in the string (both odd and even length palindromes)
 * 2. Expand around the center while characters match
 * 3. Keep track of the longest palindrome found so far
 * 4. Return the longest palindromic substring
 * 
 * Time Complexity: O(n²) - For each center, we potentially expand O(n) times
 * Space Complexity: O(1) - Only using a few variables to track the longest palindrome
 * 
 * Alternative approaches:
 * - Dynamic Programming: O(n²) time, O(n²) space
 * - Manacher's Algorithm: O(n) time, O(n) space (more complex implementation)
 * 
 * This test class validates the correctness of the implementation across various test cases.
 */
public class LongestPalindromicSubstringTest {

    /**
     * Finds the longest palindromic substring using the expand around centers approach.
     * 
     * @param s the input string
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0;
        int maxLen = 1;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (center is a single character)
            int len1 = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (center is between two characters)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get the maximum length palindrome centered at current position
            int currentMaxLen = Math.max(len1, len2);
            
            // Update the longest palindrome if we found a longer one
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                // Calculate the starting position of the palindrome
                start = i - (currentMaxLen - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Helper method to expand around a center and find the length of the palindrome.
     * 
     * @param s the input string
     * @param left the left index of the center
     * @param right the right index of the center
     * @return the length of the palindrome centered at the given indices
     */
    private int expandAroundCenter(String s, int left, int right) {
        // Expand while characters match and indices are within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // Return the length of the palindrome
        // right - left - 1 because we went one step too far in the while loop
        return right - left - 1;
    }

    /**
     * Parameterized test for longestPalindrome method
     * Tests various input strings and verifies the longest palindromic substring
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLongestPalindrome(String input, String expectedPalindrome) {
        // Given & When
        String actualPalindrome = longestPalindrome(input);
        
        // Then
        assertThat(actualPalindrome)
            .as("Longest palindromic substring for input: '%s'", input)
            .isEqualTo(expectedPalindrome);
    }

    /**
     * Additional test method to verify palindrome length when multiple valid answers exist
     */
    @ParameterizedTest
    @MethodSource("provideMultipleAnswerTestCases")
    void testLongestPalindromeLength(String input, int expectedLength, String[] validAnswers) {
        // Given & When
        String actualPalindrome = longestPalindrome(input);
        
        // Then
        assertThat(actualPalindrome)
            .as("Longest palindromic substring length for input: '%s'", input)
            .hasSize(expectedLength);
            
        assertThat(actualPalindrome)
            .as("Longest palindromic substring should be one of the valid answers for input: '%s'", input)
            .isIn((Object[]) validAnswers);
            
        // Verify it's actually a palindrome
        assertThat(isPalindrome(actualPalindrome))
            .as("Result '%s' should be a valid palindrome", actualPalindrome)
            .isTrue();
    }

    /**
     * Performance test method to verify behavior with larger inputs
     */
    @ParameterizedTest
    @MethodSource("providePerformanceTestCases")
    void testLongestPalindromePerformance(String input, int expectedMinLength) {
        // Given & When
        long startTime = System.nanoTime();
        String actualPalindrome = longestPalindrome(input);
        long endTime = System.nanoTime();
        
        // Then
        assertThat(actualPalindrome)
            .as("Performance test - Palindrome length should be at least %d for input length: %d", expectedMinLength, input.length())
            .hasSizeGreaterThanOrEqualTo(expectedMinLength);
            
        // Verify it's actually a palindrome
        assertThat(isPalindrome(actualPalindrome))
            .as("Result '%s' should be a valid palindrome", actualPalindrome)
            .isTrue();
            
        // Verify reasonable performance (should complete quickly for O(n²) algorithm)
        long executionTime = endTime - startTime;
        assertThat(executionTime)
            .as("Execution time should be reasonable for input of length %d", input.length())
            .isLessThan(50_000_000L); // 50ms in nanoseconds
    }

    /**
     * Helper method to verify if a string is a palindrome
     */
    private boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    /**
     * Provides test data for the parameterized test
     * @return Stream of Arguments containing input string and expected palindrome
     */
    static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Edge cases
            Arguments.of("", ""),
            Arguments.of("a", "a"),
            Arguments.of("ab", "a"), // or "b", both are valid
            
            // Basic palindromes
            Arguments.of("aa", "aa"),
            Arguments.of("aba", "aba"),
            Arguments.of("abba", "abba"),
            Arguments.of("racecar", "racecar"),
            Arguments.of("madam", "madam"),
            
            // Mixed cases
            Arguments.of("babad", "bab"), // "aba" is also valid
            Arguments.of("cbbd", "bb"),
            Arguments.of("ac", "a"), // "c" is also valid
            Arguments.of("ccc", "ccc"),
            Arguments.of("abcba", "abcba"),
            
            // Complex cases
            Arguments.of("abcdef", "a"), // No palindrome longer than 1
            Arguments.of("noon", "noon"),
            Arguments.of("tattarrattat", "tattarrattat"), // Entire string is palindrome
            Arguments.of("bananas", "anana"),
            Arguments.of("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendure", "ranynar"),
            
            // Numbers and mixed characters
            Arguments.of("12321", "12321"),
            Arguments.of("123454321", "123454321"),
            Arguments.of("abc12321def", "12321"),
            Arguments.of("a1b2c2b1a", "a1b2c2b1a"),
            
            // Single character repeated
            Arguments.of("aaaaaa", "aaaaaa"),
            Arguments.of("abcccba", "abcccba"),
            
            // Edge patterns
            Arguments.of("abacabad", "abacaba"),
            Arguments.of("forgeeksskeegfor", "geeksskeeg"),
            Arguments.of("abcdeffedcba", "abcdeffedcba")
        );
    }

    /**
     * Provides test cases where multiple valid answers exist
     */
    static Stream<Arguments> provideMultipleAnswerTestCases() {
        return Stream.of(
            Arguments.of("babad", 3, new String[]{"bab", "aba"}),
            Arguments.of("ac", 1, new String[]{"a", "c"}),
            Arguments.of("abcdef", 1, new String[]{"a", "b", "c", "d", "e", "f"}),
            Arguments.of("abccba", 6, new String[]{"abccba"}),
            Arguments.of("aabbaa", 6, new String[]{"aabbaa"}),
            Arguments.of("abacabad", 7, new String[]{"abacaba"})
        );
    }

    /**
     * Provides performance test cases with larger inputs
     */
    static Stream<Arguments> providePerformanceTestCases() {
        // Generate test strings
        StringBuilder sb100 = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb100.append("ab");
        }
        sb100.append("racecar"); // Insert a palindrome
        for (int i = 0; i < 50; i++) {
            sb100.append("cd");
        }
        
        StringBuilder sb500 = new StringBuilder();
        for (int i = 0; i < 250; i++) {
            sb500.append("a");
        }
        for (int i = 0; i < 250; i++) {
            sb500.append("b");
        }
        
        StringBuilder sb1000 = new StringBuilder();
        sb1000.append("a".repeat(500));
        sb1000.append("racecar");
        sb1000.append("b".repeat(493));
        
        return Stream.of(
            Arguments.of(sb100.toString(), 7),    // Should find "racecar"
            Arguments.of(sb500.toString(), 250),  // Should find "aaaa...aaa"
            Arguments.of(sb1000.toString(), 500), // Should find "aaaa...aaa"
            Arguments.of("a".repeat(1000), 1000), // Entire string is palindrome
            Arguments.of("abcd".repeat(250), 1)   // No palindrome longer than 1
        );
    }
}
