package com.zaroum.javacodingproblems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Symmetric Tree Algorithm Tests
 * 
 * A symmetric binary tree (also called a mirror tree) is a tree that looks the same
 * when you flip it horizontally. In other words, the left subtree is a mirror reflection
 * of the right subtree.
 * 
 * Definition:
 * A binary tree is symmetric if:
 * 1. The root's left and right subtrees are mirror images of each other
 * 2. For any two nodes to be mirrors:
 *    - They must have the same value
 *    - The left child of one equals the right child of the other
 *    - The right child of one equals the left child of the other
 * 
 * Examples:
 * 
 * SYMMETRIC TREE:
 *       1
 *      / \
 *     2   2
 *    / \ / \
 *   3  4 4  3
 * 
 * NOT SYMMETRIC TREE:
 *       1
 *      / \
 *     2   2
 *      \   \
 *       3   3
 * 
 * Algorithm:
 * 1. If root is null, return true (empty tree is symmetric)
 * 2. Compare root's left and right subtrees using helper method
 * 3. For two nodes to be symmetric:
 *    - Both null: true
 *    - One null, one not: false
 *    - Different values: false
 *    - Recursively check: left.left ↔ right.right AND left.right ↔ right.left
 * 
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */
@DisplayName("Symmetric Tree Tests")
public class SymmetricTreeTest {

    record Node(int value, Node left, Node right) {}

    private boolean isSymmetricTree(Node root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(Node root1, Node root2) {
        if(root1 == null && root2 == null){
            return true;
        }
        
        if(root1 == null || root2 == null){
            return false;
        }

        if(root1.value != root2.value){
            return false;
        }

        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    @ParameterizedTest
    @MethodSource("provideNullAndEmptyTrees")
    @DisplayName("Should handle null and empty trees correctly")
    void shouldHandleNullAndEmptyTreesCorrectly(Node root, boolean expected, String description) {
        // When
        boolean result = isSymmetricTree(root);
        
        // Then
        assertThat(result)
            .as("Testing null/empty cases: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSingleNodeTrees")
    @DisplayName("Should handle single node trees correctly")
    void shouldHandleSingleNodeTreesCorrectly(Node root, boolean expected, String description) {
        // When
        boolean result = isSymmetricTree(root);
        
        // Then
        assertThat(result)
            .as("Testing single node: %s", description)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSymmetricTrees")
    @DisplayName("Should return true for symmetric trees")
    void shouldReturnTrueForSymmetricTrees(Node root, String description) {
        // When
        boolean result = isSymmetricTree(root);
        
        // Then
        assertThat(result)
            .as("Testing symmetric tree: %s", description)
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideAsymmetricTrees")
    @DisplayName("Should return false for asymmetric trees")
    void shouldReturnFalseForAsymmetricTrees(Node root, String description) {
        // When
        boolean result = isSymmetricTree(root);
        
        // Then
        assertThat(result)
            .as("Testing asymmetric tree: %s", description)
            .isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideComplexSymmetricTrees")
    @DisplayName("Should handle complex symmetric trees correctly")
    void shouldHandleComplexSymmetricTreesCorrectly(Node root, String description) {
        // When
        boolean result = isSymmetricTree(root);
        
        // Then
        assertThat(result)
            .as("Testing complex symmetric tree: %s", description)
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideComplexAsymmetricTrees")
    @DisplayName("Should handle complex asymmetric trees correctly")
    void shouldHandleComplexAsymmetricTreesCorrectly(Node root, String description) {
        // When
        boolean result = isSymmetricTree(root);
        
        // Then
        assertThat(result)
            .as("Testing complex asymmetric tree: %s", description)
            .isFalse();
    }

    // Test data providers
    static Stream<Arguments> provideNullAndEmptyTrees() {
        return Stream.of(
            Arguments.of(null, true, "Null tree should be symmetric"),
            Arguments.of(new Node(1, null, null), true, "Single node tree should be symmetric")
        );
    }

    static Stream<Arguments> provideSingleNodeTrees() {
        return Stream.of(
            Arguments.of(new Node(0, null, null), true, "Single node with value 0"),
            Arguments.of(new Node(1, null, null), true, "Single node with value 1"),
            Arguments.of(new Node(-1, null, null), true, "Single node with negative value"),
            Arguments.of(new Node(100, null, null), true, "Single node with large value")
        );
    }

    static Stream<Arguments> provideSymmetricTrees() {
        return Stream.of(
            // Simple symmetric trees
            /*
                 1
                / \
               2   2
            */
            Arguments.of(
                new Node(1,
                    new Node(2, null, null),
                    new Node(2, null, null)
                ),
                "Simple symmetric tree: 1 with two children of value 2"
            ),
            
            /*
                 1
                / \
               2   2
              /     \
             3       3
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3, null, null),
                        null
                    ),
                    new Node(2,
                        null,
                        new Node(3, null, null)
                    )
                ),
                "Three-level symmetric tree"
            ),
            
            /*
                 1
                / \
               2   2
              / \ / \
             4  5 5  4
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(4, null, null),
                        new Node(5, null, null)
                    ),
                    new Node(2,
                        new Node(5, null, null),
                        new Node(4, null, null)
                    )
                ),
                "Four-node symmetric tree with mirrored structure"
            ),
            
            /*
                 5
                / \
               3   3
              / \ / \
             1  4 4  1
            */
            Arguments.of(
                new Node(5,
                    new Node(3,
                        new Node(1, null, null),
                        new Node(4, null, null)
                    ),
                    new Node(3,
                        new Node(4, null, null),
                        new Node(1, null, null)
                    )
                ),
                "Symmetric tree with different root value"
            ),
            
            /*
                 0
                / \
              -1  -1
            */
            Arguments.of(
                new Node(0,
                    new Node(-1, null, null),
                    new Node(-1, null, null)
                ),
                "Symmetric tree with negative values"
            )
        );
    }

    static Stream<Arguments> provideAsymmetricTrees() {
        return Stream.of(
            // Simple asymmetric trees
            /*
                 1
                / \
               2   3  <- Different values
            */
            Arguments.of(
                new Node(1,
                    new Node(2, null, null),
                    new Node(3, null, null)
                ),
                "Simple asymmetric tree: different child values"
            ),
            
            /*
                 1
                /
               2     <- Only left child
            */
            Arguments.of(
                new Node(1,
                    new Node(2, null, null),
                    null
                ),
                "Asymmetric tree: only left child"
            ),
            
            /*
                 1
                  \
                   2  <- Only right child
            */
            Arguments.of(
                new Node(1,
                    null,
                    new Node(2, null, null)
                ),
                "Asymmetric tree: only right child"
            ),
            
            /*
                 1
                / \
               2   2
              /   /    <- Same structure, not mirrored
             3   3
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3, null, null),
                        null
                    ),
                    new Node(2,
                        new Node(3, null, null),
                        null
                    )
                ),
                "Asymmetric tree: same structure but not mirrored"
            ),
            
            /*
                 1
                / \
               2   2
              / \ / \   <- Identical subtrees (not mirrored)
             4  5 4  5
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(4, null, null),
                        new Node(5, null, null)
                    ),
                    new Node(2,
                        new Node(4, null, null),
                        new Node(5, null, null)
                    )
                ),
                "Asymmetric tree: identical subtrees instead of mirrored"
            ),
            
            /*
                 1
                / \
               2   2
              /     \   <- Different leaf values (3 vs 4)
             3       4
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3, null, null),
                        null
                    ),
                    new Node(2,
                        null,
                        new Node(4, null, null)
                    )
                ),
                "Asymmetric tree: different leaf values"
            )
        );
    }

    static Stream<Arguments> provideComplexSymmetricTrees() {
        return Stream.of(
            // Complex symmetric trees
            /*
                     1
                   /   \
                  2     2
                 / \   / \
                3   5 5   3
               /         \
              4           4
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3,
                            new Node(4, null, null),
                            null
                        ),
                        new Node(5, null, null)
                    ),
                    new Node(2,
                        new Node(5, null, null),
                        new Node(3,
                            null,
                            new Node(4, null, null)
                        )
                    )
                ),
                "Four-level symmetric tree"
            ),
            
            /*
                     10
                   /    \
                  20     20
                 / \    / \
                30 40  40 30
                   /    \
                  50     50
            */
            Arguments.of(
                new Node(10,
                    new Node(20,
                        new Node(30, null, null),
                        new Node(40,
                            new Node(50, null, null),
                            null
                        )
                    ),
                    new Node(20,
                        new Node(40,
                            null,
                            new Node(50, null, null)
                        ),
                        new Node(30, null, null)
                    )
                ),
                "Complex symmetric tree with deeper nesting"
            ),
            
            /*
                     100
                   /     \
                 200     200
                 /         \
               300         300
              / \         / \
            400 500     500 400
            */
            Arguments.of(
                new Node(100,
                    new Node(200,
                        new Node(300,
                            new Node(400, null, null),
                            new Node(500, null, null)
                        ),
                        null
                    ),
                    new Node(200,
                        null,
                        new Node(300,
                            new Node(500, null, null),
                            new Node(400, null, null)
                        )
                    )
                ),
                "Symmetric tree with larger values and complex structure"
            )
        );
    }

    static Stream<Arguments> provideComplexAsymmetricTrees() {
        return Stream.of(
            // Complex asymmetric trees
            /*
                     1
                   /   \
                  2     2
                 / \   / \
                3   5 5   3
               /       /     <- Wrong positioning (should be right)
              4       4
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3,
                            new Node(4, null, null),
                            null
                        ),
                        new Node(5, null, null)
                    ),
                    new Node(2,
                        new Node(5, null, null),
                        new Node(3,
                            new Node(4, null, null),
                            null
                        )
                    )
                ),
                "Complex asymmetric tree: wrong positioning of leaf"
            ),
            
            /*
                     1
                   /   \
                  2     2
                 / \   / \
                3   4 4   3
                   / \ / \
                  5  6 6  7  <- Different values (6 vs 7)
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3, null, null),
                        new Node(4,
                            new Node(5, null, null),
                            new Node(6, null, null)
                        )
                    ),
                    new Node(2,
                        new Node(4,
                            new Node(6, null, null),
                            new Node(7, null, null)
                        ),
                        new Node(3, null, null)
                    )
                ),
                "Complex asymmetric tree: different leaf values in deep structure"
            ),
            
            /*
                     1
                   /   \
                  2     2
                 /       \
                3         3
               / \       /    <- Missing right child (should have 4)
              4   5     5
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3,
                            new Node(4, null, null),
                            new Node(5, null, null)
                        ),
                        null
                    ),
                    new Node(2,
                        null,
                        new Node(3,
                            new Node(5, null, null),
                            null
                        )
                    )
                ),
                "Complex asymmetric tree: missing node in mirror position"
            ),
            
            /*
                     1
                   /   \
                  2     3    <- Different root children (2 vs 3)
                 / \   / \
                3   4 4   2
            */
            Arguments.of(
                new Node(1,
                    new Node(2,
                        new Node(3, null, null),
                        new Node(4, null, null)
                    ),
                    new Node(3,
                        new Node(4, null, null),
                        new Node(2, null, null)
                    )
                ),
                "Asymmetric tree: root's children have different values"
            )
        );
    }
}
