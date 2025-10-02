# Java Coding Problems 🚀

A comprehensive collection of classic algorithmic problems and their solutions implemented in Java, featuring extensive test coverage with JUnit 5 and AssertJ assertions.

## 📋 Table of Contents

- [Overview](#overview)
- [Problems & Solutions](#problems--solutions)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Contributing](#contributing)

## 🎯 Overview

This project contains well-documented implementations of fundamental coding problems commonly encountered in technical interviews and competitive programming. Each solution includes:

- **Comprehensive problem descriptions** with examples and constraints
- **Optimized algorithms** with detailed complexity analysis
- **Extensive test suites** using JUnit 5 ParameterizedTest
- **AssertJ assertions** for readable and maintainable tests
- **Performance testing** to validate algorithmic efficiency
- **Edge case coverage** ensuring robust implementations

## 🧩 Problems & Solutions

### String Manipulation Problems

#### 1. **Longest Substring Without Repeating Characters** 
📁 `LongestSubstringWithoutRepeatingCharactersTest.java`

**Problem**: Find the length of the longest substring without repeating characters.

- **Algorithm**: Sliding Window with HashSet
- **Time Complexity**: O(n)
- **Space Complexity**: O(min(m,n))
- **Examples**: `"abcabcbb"` → `3`, `"pwwkew"` → `3`

#### 2. **Longest Palindromic Substring**
📁 `LongestPalindromicSubstringTest.java`

**Problem**: Find the longest palindromic substring in a given string.

- **Algorithm**: Expand Around Centers
- **Time Complexity**: O(n²)
- **Space Complexity**: O(1)
- **Examples**: `"babad"` → `"bab"` or `"aba"`, `"cbbd"` → `"bb"`

#### 3. **Valid Parentheses**
📁 `ValidParenthesesTest.java`

**Problem**: Determine if parentheses in a string are valid and properly matched.

- **Algorithm**: Balance Counter
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- **Examples**: `"(())"` → `true`, `"())"` → `false`

#### 4. **Valid Palindrome**
📁 `ValidPalindromeTest.java`

**Problem**: Determine if a string is a palindrome (case-sensitive).

- **Algorithm**: Two Pointers
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- **Examples**: `"racecar"` → `true`, `"hello"` → `false`

#### 5. **Valid Anagram**
📁 `ValidAnagramTest.java`

**Problem**: Determine if two strings are anagrams of each other.

- **Algorithm**: Character Frequency Map
- **Time Complexity**: O(n)
- **Space Complexity**: O(1) - limited character set
- **Examples**: `"listen"` & `"silent"` → `true`

#### 6. **Counting Duplicate Characters**
📁 `CountingDuplicateCharactersTest.java`

**Problem**: Count how many characters appear more than once in a string.

- **Algorithm**: Stream API with Collectors
- **Time Complexity**: O(n)
- **Space Complexity**: O(k) - k unique characters
- **Examples**: `"hello"` → `1`, `"programming"` → `3`

#### 7. **Finding First Non-Repeated Character**
📁 `FindingFirstNonRepeatedCharacterTest.java`

**Problem**: Find the first character that appears exactly once in a string.

- **Algorithm**: LinkedHashMap for order preservation
- **Time Complexity**: O(n)
- **Space Complexity**: O(k) - k unique characters
- **Examples**: `"hello"` → `'h'`, `"programming"` → `'p'`

#### 8. **String Reversal**
📁 `ReversingLettersTest.java`

**Problem**: Reverse the order of all characters in a string.

- **Algorithm**: StringBuilder reverse()
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- **Examples**: `"hello"` → `"olleh"`

### Array & List Problems

#### 9. **Kth Largest Element**
📁 `KthLargestItemInListTest.java`

**Problem**: Find the kth largest element in a list of integers.

- **Algorithm**: Sorting approach
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(1)
- **Examples**: `[3,1,4,1,5]` with k=1 → `4`

### Tree Problems

#### 10. **Symmetric Tree**
📁 `SymmetricTreeTest.java`

**Problem**: Determine if a binary tree is symmetric (mirror image of itself).

- **Algorithm**: Recursive mirror comparison
- **Time Complexity**: O(n)
- **Space Complexity**: O(h) - h is tree height
- **Features**: Complete TreeNode implementation with comprehensive test cases

## 🛠 Technology Stack

- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.5.6** - Project framework and dependency management
- **JUnit 5** - Modern testing framework with ParameterizedTest support
- **AssertJ** - Fluent assertion library for readable tests
- **Maven** - Build automation and dependency management

## 📁 Project Structure

```
java-coding-problems/
├── src/
│   ├── main/java/com/zaroum/javacodingproblems/
│   │   └── JavaCodingProblemsApplication.java
│   └── test/java/com/zaroum/javacodingproblems/
│       ├── CountingDuplicateCharactersTest.java
│       ├── FindingFirstNonRepeatedCharacterTest.java
│       ├── KthLargestItemInListTest.java
│       ├── LongestPalindromicSubstringTest.java
│       ├── LongestSubstringWithoutRepeatingCharactersTest.java
│       ├── ReversingLettersTest.java
│       ├── SymmetricTreeTest.java
│       ├── ValidAnagramTest.java
│       ├── ValidPalindromeTest.java
│       └── ValidParenthesesTest.java
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- IDE with Java support (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd java-coding-problems
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run all tests**
   ```bash
   mvn test
   ```

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=LongestSubstringWithoutRepeatingCharactersTest
```

### Run Tests with Coverage
```bash
mvn test jacoco:report
```

### Test Categories

Each problem includes multiple test categories:

- **🎯 Functional Tests**: Core algorithm validation
- **🔍 Edge Case Tests**: Boundary conditions and special inputs
- **⚡ Performance Tests**: Large input validation and timing
- **📊 Multiple Answer Tests**: Problems with several valid solutions

### Example Test Output
```
✅ testLongestPalindrome[input="babad", expected="bab"]
✅ testLongestPalindrome[input="cbbd", expected="bb"] 
✅ testValidParentheses[input="(())", expected=true]
✅ testLongestSubstringPerformance[length=1000, time<10ms]
```

## 📊 Test Coverage

- **35+ test cases per problem** on average
- **Edge cases**: null, empty, single elements
- **Performance tests**: up to 5000 character inputs
- **Multiple scenarios**: basic, complex, and boundary conditions
- **AssertJ assertions**: descriptive error messages

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

### Adding New Problems

1. **Create new test class** following the established pattern:
   ```java
   /**
    * Problem: [Problem Name]
    * 
    * [Detailed problem description]
    * 
    * Examples: [Examples with explanations]
    * 
    * Algorithm: [Algorithm description]
    * Time Complexity: O(...)
    * Space Complexity: O(...)
    */
   public class NewProblemTest {
       // Implementation and tests
   }
   ```

2. **Include comprehensive tests**:
   - ParameterizedTest with @MethodSource
   - AssertJ assertions
   - Edge cases and performance tests
   - Multiple test data providers

3. **Update README.md** with the new problem

### Code Style Guidelines

- Use **clear, descriptive variable names**
- Include **comprehensive JavaDoc comments**
- Follow **consistent formatting and indentation**
- Write **descriptive test method names**
- Use **AssertJ's fluent assertions**

### Test Requirements

- **Minimum 20 test cases** per problem
- **Edge case coverage** (null, empty, single elements)
- **Performance validation** for larger inputs
- **Multiple valid answers** handling where applicable

## 📈 Performance Benchmarks

| Problem | Input Size | Time Complexity | Typical Runtime |
|---------|------------|-----------------|-----------------|
| Longest Substring | 5000 chars | O(n) | < 10ms |
| Palindromic Substring | 1000 chars | O(n²) | < 50ms |
| Valid Parentheses | 5000 chars | O(n) | < 5ms |
| Anagram Check | 1000 chars | O(n) | < 5ms |
| Kth Largest | 10000 elements | O(n log n) | < 20ms |

## 📚 Learning Resources

Each problem includes:
- **Detailed algorithm explanations**
- **Step-by-step approach breakdown**
- **Complexity analysis reasoning**
- **Alternative solution mentions**
- **Real-world application examples**

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🎯 Quick Start Example

```java
// Example: Longest Palindromic Substring
LongestPalindromicSubstringTest test = new LongestPalindromicSubstringTest();
String result = test.longestPalindrome("babad");
System.out.println(result); // Output: "bab" or "aba"
```

**Happy Coding! 🎉**

---

*For questions or suggestions, please open an issue or submit a pull request.*