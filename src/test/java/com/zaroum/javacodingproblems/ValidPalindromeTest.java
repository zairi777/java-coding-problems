package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Valid Palindrome Tests")
public class ValidPalindromeTest {

    /**
     * A palindrome (whether a string or a number) looks
     * unchanged when it's reversed. This means that processing (reading) a palindrome can
     * be done from both directions and the same result will be obtained (for example, the
     * word madam is a palindrome, while the word madame is not)
     */
    private boolean checkIsValidPalindrome(String str1) {
        if(str1 == null || str1.isEmpty()){
            return false;
        }
        
        if(str1.length() == 1){
            return true;
        }
        return new StringBuilder(str1).reverse().toString().equals(str1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should return false for null or empty strings")
    void shouldReturnFalseForNullOrEmptyStrings(String input) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "z", "A", "Z", "1", "!", "@", " ", "0"})
    @DisplayName("Should return true for single character strings")
    void shouldReturnTrueForSingleCharacterStrings(String input) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideValidPalindromes")
    @DisplayName("Should return true for valid palindromes")
    void shouldReturnTrueForValidPalindromes(String input, String description) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result)
            .as("Testing valid palindrome: %s", description)
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPalindromes")
    @DisplayName("Should return false for invalid palindromes")
    void shouldReturnFalseForInvalidPalindromes(String input, String description) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result)
            .as("Testing invalid palindrome: %s", description)
            .isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideNumericPalindromes")
    @DisplayName("Should handle numeric palindromes correctly")
    void shouldHandleNumericPalindromesCorrectly(String input, boolean expected, String description) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result)
            .as("Testing numeric palindromes: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideCaseSensitivePalindromes")
    @DisplayName("Should handle case sensitivity correctly")
    void shouldHandleCaseSensitivityCorrectly(String input, boolean expected, String description) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result)
            .as("Testing case sensitivity: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSpecialCharacterPalindromes")
    @DisplayName("Should handle special characters correctly")
    void shouldHandleSpecialCharactersCorrectly(String input, boolean expected, String description) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result)
            .as("Testing special characters: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideLongPalindromes")
    @DisplayName("Should handle long palindromes correctly")
    void shouldHandleLongPalindromesCorrectly(String input, boolean expected, String description) {
        // When
        boolean result = checkIsValidPalindrome(input);
        
        // Then
        assertThat(result)
            .as("Testing long palindromes: %s", description)
            .isEqualTo(expected);
    }

    // Test data providers
    static Stream<Arguments> provideValidPalindromes() {
        return Stream.of(
            Arguments.of("aa", "Two identical characters"),
            Arguments.of("aba", "Three character palindrome"),
            Arguments.of("abba", "Four character palindrome"),
            Arguments.of("racecar", "Classic palindrome example"),
            Arguments.of("madam", "Another classic palindrome"),
            Arguments.of("level", "Palindrome word"),
            Arguments.of("noon", "Time palindrome"),
            Arguments.of("civic", "Civic palindrome"),
            Arguments.of("radar", "Radar palindrome"),
            Arguments.of("rotor", "Rotor palindrome"),
            Arguments.of("kayak", "Kayak palindrome"),
            Arguments.of("deified", "Deified palindrome"),
            Arguments.of("rotator", "Rotator palindrome"),
            Arguments.of("repaper", "Repaper palindrome"),
            Arguments.of("reviver", "Reviver palindrome")
        );
    }

    static Stream<Arguments> provideInvalidPalindromes() {
        return Stream.of(
            Arguments.of("ab", "Two different characters"),
            Arguments.of("abc", "Three different characters"),
            Arguments.of("hello", "Regular word - not palindrome"),
            Arguments.of("world", "Another regular word"),
            Arguments.of("programming", "Longer word - not palindrome"),
            Arguments.of("java", "Programming language name"),
            Arguments.of("palindrome", "The word 'palindrome' itself"),
            Arguments.of("madame", "Almost palindrome but not quite"),
            Arguments.of("algorithm", "Technical term - not palindrome"),
            Arguments.of("computer", "Technology word - not palindrome"),
            Arguments.of("abcde", "Sequential letters"),
            Arguments.of("testing", "Test word - not palindrome"),
            Arguments.of("reverse", "Reverse - not palindrome"),
            Arguments.of("forward", "Forward - not palindrome")
        );
    }

    static Stream<Arguments> provideNumericPalindromes() {
        return Stream.of(
            Arguments.of("0", true, "Single digit zero"),
            Arguments.of("1", true, "Single digit one"),
            Arguments.of("9", true, "Single digit nine"),
            Arguments.of("11", true, "Two identical digits"),
            Arguments.of("121", true, "Three digit palindrome"),
            Arguments.of("1221", true, "Four digit palindrome"),
            Arguments.of("12321", true, "Five digit palindrome"),
            Arguments.of("123321", true, "Six digit palindrome"),
            Arguments.of("1234321", true, "Seven digit palindrome"),
            Arguments.of("12", false, "Two different digits"),
            Arguments.of("123", false, "Three different digits"),
            Arguments.of("1234", false, "Four different digits"),
            Arguments.of("12345", false, "Five different digits")
        );
    }

    static Stream<Arguments> provideCaseSensitivePalindromes() {
        return Stream.of(
            Arguments.of("Aa", false, "Mixed case - not palindrome"),
            Arguments.of("AaA", true, "Mixed case three chars "),
            Arguments.of("Madam", false, "Capitalized 'Madam' - not palindrome"),
            Arguments.of("Racecar", false, "Capitalized 'Racecar' - not palindrome"),
            Arguments.of("MADAM", true, "All uppercase palindrome"),
            Arguments.of("RACECAR", true, "All uppercase palindrome"),
            Arguments.of("LEVEL", true, "All uppercase palindrome"),
            Arguments.of("madam", true, "All lowercase palindrome"),
            Arguments.of("racecar", true, "All lowercase palindrome")
        );
    }

    static Stream<Arguments> provideSpecialCharacterPalindromes() {
        return Stream.of(
            Arguments.of("!!", true, "Two exclamation marks"),
            Arguments.of("!@!", true, "Special char palindrome"),
            Arguments.of("@#@", true, "Another special char palindrome"),
            Arguments.of("!@#@!", true, "Five char special palindrome"),
            Arguments.of("a!a", true, "Letter with special char"),
            Arguments.of("1!1", true, "Number with special char"),
            Arguments.of("!@#", false, "Special chars - not palindrome"),
            Arguments.of("a!b", false, "Mixed with special - not palindrome"),
            Arguments.of("  ", true, "Two spaces"),
            Arguments.of(" ! ", true, "Space exclamation space"),
            Arguments.of("a b a", true, "Letters with spaces palindrome"),
            Arguments.of("1 2 1", true, "Numbers with spaces palindrome")
        );
    }

    static Stream<Arguments> provideLongPalindromes() {
        return Stream.of(
            Arguments.of("abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba", true, "Full alphabet palindrome"),
            Arguments.of("12345678987654321", true, "Long numeric palindrome"),
            Arguments.of("aabbccddeffeddccbbaa", true, "Long palindrome with duplicates"),
            Arguments.of("abcdefghijklmnopqrstuvwxyz", false, "Full alphabet - not palindrome"),
            Arguments.of("123456789012345678901234567890", false, "Long number - not palindrome"),
            Arguments.of("abcdefghijklmnopqrstuvwxyzabcdef", false, "Long string - not palindrome"),
            Arguments.of("raceacar", false, "Almost racecar but with extra 'a'"),
            Arguments.of("wasitacaroracatisaw", true, "Famous palindrome sentence (no spaces)"),
            Arguments.of("amanaplanacanalpanama", true, "Panama canal palindrome (no spaces)")
        );
    }
}
