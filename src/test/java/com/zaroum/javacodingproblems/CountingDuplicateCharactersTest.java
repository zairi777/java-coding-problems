package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Counting Duplicate Characters Tests")
public class CountingDuplicateCharactersTest {

    /**
     * A program that counts duplicate
     * characters from a given string.
     */
    private Long countDuplicateCharacters(String str) {
        if(str == null || str.length() == 0)
            return 0L;
        Map<Character, Long> map = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return  map.values().stream().filter(c -> c > 1).count();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should return 0 for null or empty strings")
    void shouldReturnZeroForNullOrEmptyStrings(String input) {
        // When
        Long result = countDuplicateCharacters(input);
        
        // Then
        assertThat(result).isEqualTo(0L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "z", "A", "Z", "1", "!", "@"})
    @DisplayName("Should return 0 for single character strings")
    void shouldReturnZeroForSingleCharacterStrings(String input) {
        // When
        Long result = countDuplicateCharacters(input);
        
        // Then
        assertThat(result).isEqualTo(0L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "abc", "abcd", "xyz", "123", "!@#"})
    @DisplayName("Should return 0 for strings with no duplicate characters")
    void shouldReturnZeroForStringsWithNoDuplicates(String input) {
        // When
        Long result = countDuplicateCharacters(input);
        
        // Then
        assertThat(result).isEqualTo(0L);
    }

    @ParameterizedTest
    @MethodSource("provideStringsWithDuplicates")
    @DisplayName("Should count duplicate characters correctly")
    void shouldCountDuplicateCharactersCorrectly(String input, Long expectedCount, String description) {
        // When
        Long result = countDuplicateCharacters(input);
        
        // Then
        assertThat(result)
            .as("Testing: %s", description)
            .isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @MethodSource("provideCaseSensitiveStrings")
    @DisplayName("Should handle case sensitivity correctly")
    void shouldHandleCaseSensitivityCorrectly(String input, Long expectedCount, String description) {
        // When
        Long result = countDuplicateCharacters(input);
        
        // Then
        assertThat(result)
            .as("Testing case sensitivity: %s", description)
            .isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @MethodSource("provideSpecialCharacterStrings")
    @DisplayName("Should handle special characters and numbers correctly")
    void shouldHandleSpecialCharactersAndNumbers(String input, Long expectedCount, String description) {
        // When
        Long result = countDuplicateCharacters(input);
        
        // Then
        assertThat(result)
            .as("Testing special characters: %s", description)
            .isEqualTo(expectedCount);
    }

    // Test data providers
    static Stream<Arguments> provideStringsWithDuplicates() {
        return Stream.of(
            Arguments.of("aa", 1L, "Two identical characters"),
            Arguments.of("aab", 1L, "Two 'a's and one 'b'"),
            Arguments.of("aabb", 2L, "Two 'a's and two 'b's"),
            Arguments.of("abccba", 3L, "Multiple duplicates: a, b, c"),
            Arguments.of("hello", 1L, "Two 'l's"),
            Arguments.of("programming", 3L, "Two 'r's and two 'm's and two 'g's"),
            Arguments.of("aabbcc", 3L, "Three pairs of duplicates"),
            Arguments.of("aaabbbccc", 3L, "Three characters with 3+ occurrences each"),
            Arguments.of("abcdefghijklmnopqrstuvwxyzaa", 1L, "Long string with one duplicate at end"),
            Arguments.of("mississippi", 3L, "Classic example:  i, s, p have duplicates")
        );
    }

    static Stream<Arguments> provideCaseSensitiveStrings() {
        return Stream.of(
            Arguments.of("Aa", 0L, "Different cases should be treated as different characters"),
            Arguments.of("AaBb", 0L, "Mixed case with no duplicates"),
            Arguments.of("AAaa", 2L, "Same letter in different cases, each case has duplicates"),
            Arguments.of("Hello", 1L, "Only lowercase 'l' is duplicated"),
            Arguments.of("HELLO", 1L, "Only uppercase 'L' is duplicated"),
            Arguments.of("HeLLo", 1L, "Mixed case, only 'L' is duplicated")
        );
    }

    static Stream<Arguments> provideSpecialCharacterStrings() {
        return Stream.of(
            Arguments.of("11", 1L, "Duplicate numbers"),
            Arguments.of("1122", 2L, "Two pairs of duplicate numbers"),
            Arguments.of("!!", 1L, "Duplicate special characters"),
            Arguments.of("@@##", 2L, "Two pairs of duplicate special characters"),
            Arguments.of("a1a1", 2L, "Mixed letters and numbers with duplicates"),
            Arguments.of("hello world!", 2L, "String with spaces and punctuation (l, o)"),
            Arguments.of("  ", 1L, "Duplicate spaces"),
            Arguments.of("a!b@c#a!b@c#", 6L, "Complex pattern with letters and special chars"),
            Arguments.of("123!@#123", 3L, "Numbers and special characters repeated")
        );
    }
}
