# CodeChecker Documentation

**CodeChecker** is a Java-based static analysis tool designed to evaluate
code style and cyclomatic complexity in Java source files.

This documentation explains the architecture, design decisions,
and usage of CodeChecker.

---

## 🚀 Features

### Code Style Analysis
- Indentation validation (tabs and non-4-space indents)
- Detection of TODO and FIXME comments
- Trailing whitespace detection

### Cyclomatic Complexity
- File-level cyclomatic complexity
- Method-level complexity analysis
- Detection of logical operators, loops, conditionals, and switches

### Developer Tooling
- Command-line execution
- GitHub Actions CI integration
- Clean, conditional output reporting

---

## 🧠 How CodeChecker Works

1. The entry point (`Main.java`) reads the file path provided via CLI
2. Each checker analyzes the file independently
3. Results are aggregated and printed only if issues exist
4. Complexity metrics are computed using control-flow keywords

This modular design allows easy extension of new checks.

---

## 🏗 Architecture Overview

```
Main
├── IndentationChecker
├── TodoChecker
├── TrailingWhitespaceChecker
├── SimpleCyclomaticComplexityChecker
└── AdvancedCyclomaticComplexityChecker
```


Each checker follows the **single-responsibility principle**.

---

## 📊 Example Output

```
=== CodeChecker ===

Simple Cyclomatic Complexity:
Total Complexity: 10

Advanced Cyclomatic Complexity per method:
Method: main → Complexity: 10
File Total Complexity: 10

No code style issues found!
```


---

## 🛣 Future Enhancements

- Configuration file support
- Package-level analysis
- Integration with build tools (Maven / Gradle)
- IDE plugin support

---

## 🎓 Academic & Portfolio Context

This project demonstrates:
- Strong Java fundamentals
- Understanding of software quality metrics
- Practical static analysis concepts
- Clean architecture and automation using CI/CD

Designed as a portfolio project.
