package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

/**
 * Kth Largest Element Algorithm Tests
 * 
 * Problem Description:
 * Given a list of integers and an integer k, find the kth largest element in the list.
 * The kth largest element is the element that would be at index k (0-based) if the list
 * were sorted in descending order. Note: k=0 means the largest element, k=1 means
 * the second largest, etc.
 * 
 * Examples:
 * - [3,1,4,1,5] with k=0 → 5 (largest element)
 * - [3,1,4,1,5] with k=1 → 4 (second largest)
 * - [3,1,4,1,5] with k=2 → 3 (third largest)
 * - [10,20,30] with k=2 → 10 (smallest in this case)
 * 
 * Algorithm:
 * 1. Handle edge cases (null/empty list)
 * 2. Sort the list in descending order
 * 3. Return element at index k
 * 4. Throw IndexOutOfBoundsException for invalid k values
 * 
 * Implementation Details:
 * - Uses in-place sorting with custom comparator (descending order)
 * - Handles duplicate elements (they maintain their positions after sorting)
 * - Supports negative numbers and zero
 * - Validates k bounds (must be within [0, list.size()-1])
 * 
 * Time Complexity: O(n log n) where n is the size of the list (due to sorting)
 * Space Complexity: O(1) for in-place sorting (excluding input list)
 */
@DisplayName("Kth Largest Element In List Tests")
public class KthLargestItemInListTest {

    private Integer getKthLargestElementInTheList(List<Integer> list, int k) {
       if(list == null || list.isEmpty()){
           return null;
       }

       list.sort((o1, o2) -> o2 - o1);
       return list.get(k);
    }

    @ParameterizedTest
    @MethodSource("provideNullAndEmptyLists")
    @DisplayName("Should return null for null or empty lists")
    void shouldReturnNullForNullOrEmptyLists(List<Integer> list, int k, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing null/empty cases: %s", description)
            .isNull();
    }

    @ParameterizedTest
    @MethodSource("provideSingleElementLists")
    @DisplayName("Should handle single element lists correctly")
    void shouldHandleSingleElementListsCorrectly(List<Integer> list, int k, Integer expected, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing single element: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideValidKthLargestCases")
    @DisplayName("Should find kth largest element correctly")
    void shouldFindKthLargestElementCorrectly(List<Integer> list, int k, Integer expected, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing kth largest: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideListsWithDuplicates")
    @DisplayName("Should handle duplicate elements correctly")
    void shouldHandleDuplicateElementsCorrectly(List<Integer> list, int k, Integer expected, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing duplicates: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideNegativeNumberLists")
    @DisplayName("Should handle negative numbers correctly")
    void shouldHandleNegativeNumbersCorrectly(List<Integer> list, int k, Integer expected, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing negative numbers: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideMixedNumberLists")
    @DisplayName("Should handle mixed positive and negative numbers correctly")
    void shouldHandleMixedNumbersCorrectly(List<Integer> list, int k, Integer expected, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing mixed numbers: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSortedLists")
    @DisplayName("Should handle already sorted lists correctly")
    void shouldHandleAlreadySortedListsCorrectly(List<Integer> list, int k, Integer expected, String description) {
        // When
        Integer result = getKthLargestElementInTheList(list, k);
        
        // Then
        assertThat(result)
            .as("Testing sorted lists: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidKValues")
    @DisplayName("Should handle invalid k values")
    void shouldHandleInvalidKValues(List<Integer> list, int k, String description) {
        // When & Then
        assertThatThrownBy(() -> getKthLargestElementInTheList(list, k))
            .as("Testing invalid k values: %s", description)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    // Test data providers
    static Stream<Arguments> provideNullAndEmptyLists() {
        return Stream.of(
            Arguments.of(null, 0, "Null list with k=0"),
            Arguments.of(null, 1, "Null list with k=1"),
            Arguments.of(Collections.emptyList(), 0, "Empty list with k=0"),
            Arguments.of(Collections.emptyList(), 1, "Empty list with k=1")
        );
    }

    static Stream<Arguments> provideSingleElementLists() {
        return Stream.of(
            Arguments.of(Arrays.asList(5), 0, 5, "Single element list, k=0 (largest)"),
            Arguments.of(Arrays.asList(10), 0, 10, "Single element list with 10"),
            Arguments.of(Arrays.asList(-3), 0, -3, "Single negative element"),
            Arguments.of(Arrays.asList(0), 0, 0, "Single zero element")
        );
    }

    static Stream<Arguments> provideValidKthLargestCases() {
        return Stream.of(
            Arguments.of(Arrays.asList(3, 1, 4, 1, 5), 0, 5, "k=0 (largest) in [3,1,4,1,5]"),
            Arguments.of(Arrays.asList(3, 1, 4, 1, 5), 1, 4, "k=1 (2nd largest) in [3,1,4,1,5]"),
            Arguments.of(Arrays.asList(3, 1, 4, 1, 5), 2, 3, "k=2 (3rd largest) in [3,1,4,1,5]"),
            Arguments.of(Arrays.asList(3, 1, 4, 1, 5), 3, 1, "k=3 (4th largest) in [3,1,4,1,5]"),
            Arguments.of(Arrays.asList(3, 1, 4, 1, 5), 4, 1, "k=4 (5th largest) in [3,1,4,1,5]"),
            Arguments.of(Arrays.asList(10, 20, 30), 0, 30, "k=0 in [10,20,30]"),
            Arguments.of(Arrays.asList(10, 20, 30), 1, 20, "k=1 in [10,20,30]"),
            Arguments.of(Arrays.asList(10, 20, 30), 2, 10, "k=2 in [10,20,30]"),
            Arguments.of(Arrays.asList(7, 2, 9, 1, 5, 6), 0, 9, "k=0 in larger array"),
            Arguments.of(Arrays.asList(7, 2, 9, 1, 5, 6), 2, 6, "k=2 in larger array")
        );
    }

    static Stream<Arguments> provideListsWithDuplicates() {
        return Stream.of(
            Arguments.of(Arrays.asList(5, 5, 5), 0, 5, "All same elements, k=0"),
            Arguments.of(Arrays.asList(5, 5, 5), 1, 5, "All same elements, k=1"),
            Arguments.of(Arrays.asList(5, 5, 5), 2, 5, "All same elements, k=2"),
            Arguments.of(Arrays.asList(3, 3, 2, 2, 1), 0, 3, "Duplicates at start, k=0"),
            Arguments.of(Arrays.asList(3, 3, 2, 2, 1), 1, 3, "Duplicates at start, k=1"),
            Arguments.of(Arrays.asList(3, 3, 2, 2, 1), 2, 2, "Duplicates at start, k=2"),
            Arguments.of(Arrays.asList(1, 2, 2, 3, 3), 0, 3, "Multiple duplicates, k=0"),
            Arguments.of(Arrays.asList(1, 1, 2, 3, 3, 3), 0, 3, "Multiple duplicates, k=0"),
            Arguments.of(Arrays.asList(1, 1, 2, 3, 3, 3), 3, 2, "Multiple duplicates, k=3")
        );
    }

    static Stream<Arguments> provideNegativeNumberLists() {
        return Stream.of(
            Arguments.of(Arrays.asList(-1, -2, -3), 0, -1, "All negative, k=0 (least negative)"),
            Arguments.of(Arrays.asList(-1, -2, -3), 1, -2, "All negative, k=1"),
            Arguments.of(Arrays.asList(-1, -2, -3), 2, -3, "All negative, k=2 (most negative)"),
            Arguments.of(Arrays.asList(-10, -5, -15), 0, -5, "Negative numbers, k=0"),
            Arguments.of(Arrays.asList(-10, -5, -15), 1, -10, "Negative numbers, k=1"),
            Arguments.of(Arrays.asList(-100, -50, -25), 0, -25, "Large negative numbers")
        );
    }

    static Stream<Arguments> provideMixedNumberLists() {
        return Stream.of(
            Arguments.of(Arrays.asList(-2, 0, 3, -1, 5), 0, 5, "Mixed numbers, k=0 (largest positive)"),
            Arguments.of(Arrays.asList(-2, 0, 3, -1, 5), 1, 3, "Mixed numbers, k=1"),
            Arguments.of(Arrays.asList(-2, 0, 3, -1, 5), 2, 0, "Mixed numbers, k=2 (zero)"),
            Arguments.of(Arrays.asList(-2, 0, 3, -1, 5), 3, -1, "Mixed numbers, k=3"),
            Arguments.of(Arrays.asList(-2, 0, 3, -1, 5), 4, -2, "Mixed numbers, k=4 (most negative)"),
            Arguments.of(Arrays.asList(10, -5, 0, 15, -10), 0, 15, "Mixed with larger range"),
            Arguments.of(Arrays.asList(10, -5, 0, 15, -10), 2, 0, "Mixed with zero in middle")
        );
    }

    static Stream<Arguments> provideSortedLists() {
        return Stream.of(
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5), 0, 5, "Ascending sorted, k=0"),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5), 2, 3, "Ascending sorted, k=2"),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5), 4, 1, "Ascending sorted, k=4"),
            Arguments.of(Arrays.asList(5, 4, 3, 2, 1), 0, 5, "Descending sorted, k=0"),
            Arguments.of(Arrays.asList(5, 4, 3, 2, 1), 2, 3, "Descending sorted, k=2"),
            Arguments.of(Arrays.asList(5, 4, 3, 2, 1), 4, 1, "Descending sorted, k=4"),
            Arguments.of(Arrays.asList(10, 20, 30, 40, 50), 1, 40, "Larger ascending sorted"),
            Arguments.of(Arrays.asList(50, 40, 30, 20, 10), 1, 40, "Larger descending sorted")
        );
    }

    static Stream<Arguments> provideInvalidKValues() {
        return Stream.of(
            Arguments.of(Arrays.asList(1, 2, 3), -1, "Negative k value"),
            Arguments.of(Arrays.asList(1, 2, 3), 3, "k equals list size"),
            Arguments.of(Arrays.asList(1, 2, 3), 5, "k greater than list size"),
            Arguments.of(Arrays.asList(10), 1, "k=1 with single element list"),
            Arguments.of(Arrays.asList(1, 2), 2, "k=2 with two element list")
        );
    }
}
