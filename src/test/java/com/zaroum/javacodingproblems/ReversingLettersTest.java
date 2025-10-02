package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Reversing Letters Tests")
public class ReversingLettersTest {

    /**
     * A program that reverses the letters of
     * each word
     */
    private String reverseLetters(String str) {
        if(str == null || str.length() == 0)
            return null;

        return new StringBuilder(str).reverse().toString();

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should return null for null or empty strings")
    void shouldReturnNullForNullOrEmptyStrings(String input) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "z", "A", "Z", "1", "!", "@", " "})
    @DisplayName("Should return the same character for single character strings")
    void shouldReturnSameCharacterForSingleCharacterStrings(String input) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result).isEqualTo(input);
    }

    @ParameterizedTest
    @MethodSource("provideSimpleStringsToReverse")
    @DisplayName("Should reverse simple strings correctly")
    void shouldReverseSimpleStringsCorrectly(String input, String expectedReversed, String description) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result)
            .as("Testing: %s", description)
            .isEqualTo(expectedReversed);
    }

    @ParameterizedTest
    @MethodSource("providePalindromeStrings")
    @DisplayName("Should handle palindromes correctly")
    void shouldHandlePalindromesCorrectly(String input, String description) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result)
            .as("Testing palindrome: %s", description)
            .isEqualTo(input);
    }

    @ParameterizedTest
    @MethodSource("provideWordsToReverse")
    @DisplayName("Should reverse words correctly")
    void shouldReverseWordsCorrectly(String input, String expectedReversed, String description) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result)
            .as("Testing word reversal: %s", description)
            .isEqualTo(expectedReversed);
    }

    @ParameterizedTest
    @MethodSource("provideCaseSensitiveStrings")
    @DisplayName("Should preserve case when reversing")
    void shouldPreserveCaseWhenReversing(String input, String expectedReversed, String description) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result)
            .as("Testing case preservation: %s", description)
            .isEqualTo(expectedReversed);
    }

    @ParameterizedTest
    @MethodSource("provideSpecialCharacterStrings")
    @DisplayName("Should handle special characters and numbers correctly")
    void shouldHandleSpecialCharactersAndNumbers(String input, String expectedReversed, String description) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result)
            .as("Testing special characters: %s", description)
            .isEqualTo(expectedReversed);
    }

    @ParameterizedTest
    @MethodSource("provideComplexStrings")
    @DisplayName("Should handle complex strings correctly")
    void shouldHandleComplexStringsCorrectly(String input, String expectedReversed, String description) {
        // When
        String result = reverseLetters(input);
        
        // Then
        assertThat(result)
            .as("Testing complex strings: %s", description)
            .isEqualTo(expectedReversed);
    }

    // Test data providers
    static Stream<Arguments> provideSimpleStringsToReverse() {
        return Stream.of(
            Arguments.of("ab", "ba", "Two character string"),
            Arguments.of("abc", "cba", "Three character string"),
            Arguments.of("abcd", "dcba", "Four character string"),
            Arguments.of("hello", "olleh", "Simple word"),
            Arguments.of("world", "dlrow", "Another simple word"),
            Arguments.of("test", "tset", "Test word"),
            Arguments.of("reverse", "esrever", "The word 'reverse'"),
            Arguments.of("programming", "gnimmargorp", "Longer word"),
            Arguments.of("java", "avaj", "Programming language"),
            Arguments.of("string", "gnirts", "Data type name")
        );
    }

    static Stream<Arguments> providePalindromeStrings() {
        return Stream.of(
            Arguments.of("aa", "Two identical characters"),
            Arguments.of("aba", "Three character palindrome"),
            Arguments.of("abba", "Four character palindrome"),
            Arguments.of("racecar", "Classic palindrome"),
            Arguments.of("madam", "Another palindrome"),
            Arguments.of("level", "Palindrome word"),
            Arguments.of("noon", "Time palindrome"),
            Arguments.of("civic", "Civic palindrome"),
            Arguments.of("radar", "Radar palindrome")
        );
    }

    static Stream<Arguments> provideWordsToReverse() {
        return Stream.of(
            Arguments.of("algorithm", "mhtirogla", "Technical term"),
            Arguments.of("computer", "retupmoc", "Technology word"),
            Arguments.of("software", "erawtfos", "Development term"),
            Arguments.of("database", "esabatad", "Storage term"),
            Arguments.of("function", "noitcnuf", "Programming concept"),
            Arguments.of("variable", "elbairav", "Programming element"),
            Arguments.of("method", "dohtem", "OOP concept"),
            Arguments.of("class", "ssalc", "OOP structure"),
            Arguments.of("object", "tcejbo", "OOP instance"),
            Arguments.of("interface", "ecafretni", "OOP contract")
        );
    }

    static Stream<Arguments> provideCaseSensitiveStrings() {
        return Stream.of(
            Arguments.of("Hello", "olleH", "Capitalized word"),
            Arguments.of("WORLD", "DLROW", "All uppercase"),
            Arguments.of("Java", "avaJ", "Mixed case"),
            Arguments.of("CamelCase", "esaClemaC", "Camel case pattern"),
            Arguments.of("PascalCase", "esaClacsaP", "Pascal case pattern"),
            Arguments.of("MixedCASE", "ESACdexiM", "Mixed upper and lower"),
            Arguments.of("AbCdEf", "fEdCbA", "Alternating case"),
            Arguments.of("HTML", "LMTH", "Acronym"),
            Arguments.of("JavaScript", "tpircSavaJ", "Technology name")
        );
    }

    static Stream<Arguments> provideSpecialCharacterStrings() {
        return Stream.of(
            Arguments.of("123", "321", "Numbers only"),
            Arguments.of("abc123", "321cba", "Letters and numbers"),
            Arguments.of("!@#", "#@!", "Special characters only"),
            Arguments.of("hello!", "!olleh", "Word with exclamation"),
            Arguments.of("test@123", "321@tset", "Mixed with @ symbol"),
            Arguments.of("a1b2c3", "3c2b1a", "Alternating letters and numbers"),
            Arguments.of("hello world", "dlrow olleh", "String with space"),
            Arguments.of("  spaces  ", "  secaps  ", "String with multiple spaces"),
            Arguments.of("tab\there", "ereh\tbat", "String with tab character"),
            Arguments.of("new\nline", "enil\nwen", "String with newline")
        );
    }

    static Stream<Arguments> provideComplexStrings() {
        return Stream.of(
            Arguments.of("The Quick Brown Fox", "xoF nworB kciuQ ehT", "Sentence with spaces"),
            Arguments.of("Hello, World!", "!dlroW ,olleH", "Greeting with punctuation"),
            Arguments.of("user@example.com", "moc.elpmaxe@resu", "Email address"),
            Arguments.of("https://www.example.com", "moc.elpmaxe.www//:sptth", "URL"),
            Arguments.of("password123!", "!321drowssap", "Password-like string"),
            Arguments.of("file_name.txt", "txt.eman_elif", "Filename with extension"),
            Arguments.of("2023-12-31", "13-21-3202", "Date format"),
            Arguments.of("$100.50", "05.001$", "Currency format"),
            Arguments.of("(555) 123-4567", "7654-321 )555(", "Phone number format"),
            Arguments.of("Lorem ipsum dolor", "rolod muspi meroL", "Latin text sample")
        );
    }
}
