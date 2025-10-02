package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Anagram Validation Algorithm Tests
 * 
 * Problem Description:
 * Given two strings, determine if they are anagrams of each other.
 * Two strings are anagrams if they contain the same characters with the same frequencies,
 * but possibly in different order. The comparison is case-sensitive.
 * 
 * Examples:
 * - "listen" and "silent" → true (same characters, different order)
 * - "elbow" and "below" → true (anagrams)
 * - "hello" and "world" → false (different characters)
 * - "Listen" and "Silent" → false (case-sensitive, different cases)
 * 
 * Algorithm:
 * 1. Handle edge cases (null strings, different lengths)
 * 2. Check if strings are identical (optimization)
 * 3. Create character frequency maps for both strings
 * 4. Compare frequency maps to ensure same character counts
 * 
 * Implementation Details:
 * - Uses Java 8 Streams with groupingBy and counting collectors
 * - Case-sensitive comparison (A ≠ a)
 * - Handles all Unicode characters including special symbols
 * - Short-circuit optimization for different lengths
 * 
 * Time Complexity: O(n) where n is the length of the strings
 * Space Complexity: O(k) where k is the number of unique characters
 */
@DisplayName("Valid Anagram Tests")
public class ValidAnagramTest {

 
    private boolean checkIsValidAnagram(String str1, String str2) {
        if(str1 == null || str2 == null){
            return false;
        }
        
        if(str1.length() != str2.length()){
            return false;
        }
        
        if(str1.equals(str2)){
            return true;
        }

        Map<Character, Long> map1 = countsByCharacter(str1);
        Map<Character, Long> map2 = countsByCharacter(str2);

        for(Map.Entry<Character, Long> entry : map1.entrySet()){
            if(!map2.containsKey(entry.getKey()) || !map2.get(entry.getKey()).equals(entry.getValue())){
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Long> countsByCharacter(String str) {
        return str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @ParameterizedTest
    @MethodSource("provideNullAndEmptyStrings")
    @DisplayName("Should handle null and empty strings correctly")
    void shouldHandleNullAndEmptyStringsCorrectly(String str1, String str2, boolean expected, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing null/empty cases: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideValidAnagrams")
    @DisplayName("Should return true for valid anagrams")
    void shouldReturnTrueForValidAnagrams(String str1, String str2, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing valid anagrams: %s", description)
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAnagrams")
    @DisplayName("Should return false for invalid anagrams")
    void shouldReturnFalseForInvalidAnagrams(String str1, String str2, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing invalid anagrams: %s", description)
            .isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideIdenticalStrings")
    @DisplayName("Should return true for identical strings")
    void shouldReturnTrueForIdenticalStrings(String str1, String str2, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing identical strings: %s", description)
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideCaseSensitiveAnagrams")
    @DisplayName("Should handle case sensitivity correctly")
    void shouldHandleCaseSensitivityCorrectly(String str1, String str2, boolean expected, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing case sensitivity: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSpecialCharacterAnagrams")
    @DisplayName("Should handle special characters and numbers correctly")
    void shouldHandleSpecialCharactersAndNumbers(String str1, String str2, boolean expected, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing special characters: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideDifferentLengthStrings")
    @DisplayName("Should return false for strings of different lengths")
    void shouldReturnFalseForDifferentLengthStrings(String str1, String str2, String description) {
        // When
        boolean result = checkIsValidAnagram(str1, str2);
        
        // Then
        assertThat(result)
            .as("Testing different length strings: %s", description)
            .isFalse();
    }

    // Test data providers
    static Stream<Arguments> provideNullAndEmptyStrings() {
        return Stream.of(
            Arguments.of(null, null, false, "Both strings are null"),
            Arguments.of(null, "test", false, "First string is null"),
            Arguments.of("test", null, false, "Second string is null"),
            Arguments.of("", "", true, "Both strings are empty"),
            Arguments.of("", "a", false, "Empty string vs non-empty"),
            Arguments.of("a", "", false, "Non-empty vs empty string")
        );
    }

    static Stream<Arguments> provideValidAnagrams() {
        return Stream.of(
            Arguments.of("listen", "silent", "Classic anagram example"),
            Arguments.of("elbow", "below", "Another classic anagram"),
            Arguments.of("a", "a", "Single character anagram"),
            Arguments.of("ab", "ba", "Two character anagram"),
            Arguments.of("abc", "bca", "Three character anagram"),
            Arguments.of("abc", "cab", "Three character anagram - different order"),
            Arguments.of("anagram", "nagaram", "Word 'anagram' rearranged"),
            Arguments.of("rat", "tar", "Simple three letter anagram"),
            Arguments.of("evil", "vile", "Four letter anagram"),
            Arguments.of("dusty", "study", "Five letter anagram"),
            Arguments.of("night", "thing", "Another five letter anagram"),
            Arguments.of("stressed", "desserts", "Eight letter anagram"),
            Arguments.of("the eyes", "they see", "Phrase with spaces")
        );
    }

    static Stream<Arguments> provideInvalidAnagrams() {
        return Stream.of(
            Arguments.of("hello", "world", "Completely different words"),
            Arguments.of("listen", "ennlist", "Different character frequencies"),
            Arguments.of("abc", "def", "No common characters"),
            Arguments.of("aab", "abb", "Different character counts"),
            Arguments.of("abcd", "abce", "One different character"),
            Arguments.of("programming", "gramingpro", "Missing one character"),
            Arguments.of("test", "best", "One character different"),
            Arguments.of("anagram", "grammar", "Similar but not anagram"),
            Arguments.of("java", "python", "Different programming languages"),
            Arguments.of("algorithn", "logarithm", "Similar length, different chars")
        );
    }

    static Stream<Arguments> provideIdenticalStrings() {
        return Stream.of(
            Arguments.of("test", "test", "Identical simple word"),
            Arguments.of("hello", "hello", "Identical greeting"),
            Arguments.of("programming", "programming", "Identical longer word"),
            Arguments.of("a", "a", "Identical single character"),
            Arguments.of("123", "123", "Identical numbers"),
            Arguments.of("!@#", "!@#", "Identical special characters"),
            Arguments.of("Hello World", "Hello World", "Identical phrase with space"),
            Arguments.of("", "", "Identical empty strings")
        );
    }

    static Stream<Arguments> provideCaseSensitiveAnagrams() {
        return Stream.of(
            Arguments.of("Listen", "Silent", false, "Different cases - not anagrams"),
            Arguments.of("LISTEN", "SILENT", true, "Same case uppercase - valid anagram"),
            Arguments.of("listen", "silent", true, "Same case lowercase - valid anagram"),
            Arguments.of("Hello", "olleh", false, "Mixed case - not anagrams"),
            Arguments.of("ABC", "BCA", true, "Uppercase anagram"),
            Arguments.of("abc", "BCA", false, "Mixed case - not anagrams"),
            Arguments.of("Test", "sett", false, "Different case and chars"),
            Arguments.of("Java", "AvaJ", false, "Mixed case - not anagrams")
        );
    }

    static Stream<Arguments> provideSpecialCharacterAnagrams() {
        return Stream.of(
            Arguments.of("123", "321", true, "Number anagram"),
            Arguments.of("abc123", "321cba", true, "Mixed letters and numbers"),
            Arguments.of("!@#", "#@!", true, "Special character anagram"),
            Arguments.of("hello!", "!olleh", true, "Word with exclamation"),
            Arguments.of("test@123", "321@tset", true, "Mixed with @ symbol"),
            Arguments.of("a1b2", "2b1a", true, "Alternating letters and numbers"),
            Arguments.of("hello world", "dlrow olleh", true, "Phrase with space"),
            Arguments.of("a b c", "c b a", true, "Letters with spaces"),
            Arguments.of("123", "124", false, "Different numbers"),
            Arguments.of("!@#", "!@$", false, "Different special characters")
        );
    }

    static Stream<Arguments> provideDifferentLengthStrings() {
        return Stream.of(
            Arguments.of("a", "ab", "Single vs double character"),
            Arguments.of("hello", "helloo", "One extra character"),
            Arguments.of("test", "testing", "Substring relationship"),
            Arguments.of("short", "verylongstring", "Very different lengths"),
            Arguments.of("", "a", "Empty vs single character"),
            Arguments.of("abc", "abcdef", "Three vs six characters"),
            Arguments.of("anagram", "anagrams", "Plural vs singular"),
            Arguments.of("java", "javascript", "Substring in longer word")
        );
    }
}
