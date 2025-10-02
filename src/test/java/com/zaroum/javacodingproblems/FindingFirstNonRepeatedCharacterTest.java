package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Finding First Non-Repeated Character Algorithm Tests
 * 
 * Problem Description:
 * Given a string, find and return the first character that appears exactly once
 * in the string. If no such character exists, return null.
 * The algorithm maintains the order of characters as they appear in the original string.
 * 
 * Examples:
 * - "hello" → 'h' (first character that appears only once)
 * - "programming" → 'p' (first non-repeated character)
 * - "aabbcc" → null (all characters are repeated)
 * - "abac" → 'b' (first character with single occurrence)
 * 
 * Algorithm:
 * 1. Handle edge cases (null/empty string)
 * 2. Use LinkedHashMap to maintain insertion order while counting frequencies
 * 3. Iterate through characters and count occurrences using compute()
 * 4. Find first character with frequency = 1
 * 
 * Implementation Details:
 * - Uses LinkedHashMap to preserve character order
 * - Case-sensitive character matching
 * - Handles all Unicode characters including special symbols
 * - Returns Character object (can be null)
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */
@DisplayName("Finding First Non-Repeated Character Tests")
public class FindingFirstNonRepeatedCharacterTest {


    private Character findFirstNonRepeatedCharacter(String str) {
        if(str == null || str.length() == 0)
            return null;

        Map<Character, Integer> map = new LinkedHashMap<>();
        for(char c : str.toCharArray()) {
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1)
                return entry.getKey();
        }
        return null;
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should return null for null or empty strings")
    void shouldReturnNullForNullOrEmptyStrings(String input) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "z", "A", "Z", "1", "!", "@", " "})
    @DisplayName("Should return the single character for single character strings")
    void shouldReturnSingleCharacterForSingleCharacterStrings(String input) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result).isEqualTo(input.charAt(0));
    }

    @ParameterizedTest
    @MethodSource("provideStringsWithFirstNonRepeatedCharacter")
    @DisplayName("Should find first non-repeated character correctly")
    void shouldFindFirstNonRepeatedCharacterCorrectly(String input, Character expectedChar, String description) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result)
            .as("Testing: %s", description)
            .isEqualTo(expectedChar);
    }

    @ParameterizedTest
    @MethodSource("provideStringsWithNoNonRepeatedCharacter")
    @DisplayName("Should return null when no non-repeated character exists")
    void shouldReturnNullWhenNoNonRepeatedCharacterExists(String input, String description) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result)
            .as("Testing: %s", description)
            .isNull();
    }

    @ParameterizedTest
    @MethodSource("provideCaseSensitiveStrings")
    @DisplayName("Should handle case sensitivity correctly")
    void shouldHandleCaseSensitivityCorrectly(String input, Character expectedChar, String description) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result)
            .as("Testing case sensitivity: %s", description)
            .isEqualTo(expectedChar);
    }

    @ParameterizedTest
    @MethodSource("provideSpecialCharacterStrings")
    @DisplayName("Should handle special characters and numbers correctly")
    void shouldHandleSpecialCharactersAndNumbers(String input, Character expectedChar, String description) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result)
            .as("Testing special characters: %s", description)
            .isEqualTo(expectedChar);
    }

    @ParameterizedTest
    @MethodSource("provideComplexStrings")
    @DisplayName("Should handle complex strings correctly")
    void shouldHandleComplexStringsCorrectly(String input, Character expectedChar, String description) {
        // When
        Character result = findFirstNonRepeatedCharacter(input);
        
        // Then
        assertThat(result)
            .as("Testing complex strings: %s", description)
            .isEqualTo(expectedChar);
    }

    // Test data providers
    static Stream<Arguments> provideStringsWithFirstNonRepeatedCharacter() {
        return Stream.of(
            Arguments.of("ab", 'a', "First character is non-repeated"),
            Arguments.of("ba", 'b', "First character is non-repeated"),
            Arguments.of("abc", 'a', "All characters are non-repeated, return first"),
            Arguments.of("abac", 'b', "First non-repeated is 'b'"),
            Arguments.of("abccba", null, "All characters are repeated"),
            Arguments.of("hello", 'h', "First non-repeated character in 'hello'"),
            Arguments.of("programming", 'p', "First non-repeated character in 'programming'"),
            Arguments.of("aabbcdef", 'c', "First non-repeated after repeated pairs"),
            Arguments.of("racecar", 'e', "Palindrome with non-repeated character"),
            Arguments.of("abcdefghijklmnopqrstuvwxyz", 'a', "All unique characters, return first")
        );
    }

    static Stream<Arguments> provideStringsWithNoNonRepeatedCharacter() {
        return Stream.of(
            Arguments.of("aa", "All characters repeated"),
            Arguments.of("aabb", "All characters repeated in pairs"),
            Arguments.of("abab", "Pattern with all characters repeated"),
            Arguments.of("aabbcc", "Multiple pairs, all repeated"),
            Arguments.of("abcabc", "Pattern repeated twice"),
            Arguments.of("xyzxyz", "Another pattern repeated twice"),
            Arguments.of("aabbaaccdd", "Complex pattern with all characters repeated")
        );
    }

    static Stream<Arguments> provideCaseSensitiveStrings() {
        return Stream.of(
            Arguments.of("Aa", 'A', "Uppercase A is first non-repeated"),
            Arguments.of("aA", 'a', "Lowercase a is first non-repeated"),
            Arguments.of("AaBb", 'A', "Mixed case, A is first non-repeated"),
            Arguments.of("aAbBcC", 'a', "Mixed case pattern, a is first non-repeated"),
            Arguments.of("Hello", 'H', "Capitalized word, H is first non-repeated"),
            Arguments.of("HELLO", 'H', "All caps, H is first non-repeated"),
            Arguments.of("hELLO", 'h', "Mixed case, h is first non-repeated")
        );
    }

    static Stream<Arguments> provideSpecialCharacterStrings() {
        return Stream.of(
            Arguments.of("123", '1', "Numbers, first is non-repeated"),
            Arguments.of("112", '2', "Numbers with repetition"),
            Arguments.of("!@#", '!', "Special characters, first is non-repeated"),
            Arguments.of("!!@", '@', "Special characters with repetition"),
            Arguments.of("a1b2", 'a', "Mixed letters and numbers"),
            Arguments.of("1a1b", 'a', "Numbers and letters with repetition"),
            Arguments.of("  a", 'a', "String with spaces"),
            Arguments.of("a b", 'a', "String with space in middle"),
            Arguments.of("!@#$%", '!', "Multiple special characters")
        );
    }

    static Stream<Arguments> provideComplexStrings() {
        return Stream.of(
            Arguments.of("abcdefghijklmnopqrstuvwxyzaa", 'b', "Long string with duplicate at end"),
            Arguments.of("the quick brown fox", 't', "Sentence with spaces"),
            Arguments.of("mississippi", 'm', "Classic string with many repetitions"),
            Arguments.of("aabbccddeeffgg", null, "Long string with all characters repeated"),
            Arguments.of("javascript", 'j', "Programming language name"),
            Arguments.of("algorithm", 'a', "Technical term"),
            Arguments.of("abcdefg hijklmn", 'a', "String with space separator"),
            Arguments.of("1234567890", '1', "All digits"),
            Arguments.of("!@#$%^&*()", '!', "All special characters"),
            Arguments.of("AaAaAa", null, "Alternating case pattern, all repeated")
        );
    }
}
