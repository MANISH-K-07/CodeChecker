# CodeChecker

**Java-based Code Linter and Cyclomatic Complexity Analyzer**

- [Java CI](https://github.com/MANISH-K-07/CodeChecker/actions/workflows/java-ci.yml/badge.svg)
- [Actions](https://github.com/MANISH-K-07/CodeChecker/actions)

---

## **Project Overview**

CodeChecker is a professional-grade **Java code analysis tool** designed for learning, portfolio, and profile-building purposes.  
It provides **code style checks** and **cyclomatic complexity analysis** for Java source files, giving developers insights into code quality, readability, and maintainability.

This project is particularly useful for:

- Developers wanting to understand code quality metrics  
- Demonstrating GitHub portfolio projects with **clean code, automation, and CI/CD**
- Engineering Students learning system design and building real-time projects 

---

## **Features**

1. **Code Style Checks**
   - **IndentationChecker**: Detects tabs and non-multiples-of-4 spaces  
   - **TodoChecker**: Detects `TODO` and `FIXME` comments  
   - **TrailingWhitespaceChecker**: Detects trailing spaces at the end of lines  

2. **Cyclomatic Complexity Analysis**
   - **SimpleCyclomaticComplexityChecker**: Returns file-level cyclomatic complexity  
   - **AdvancedCyclomaticComplexityChecker**: Calculates **method-level complexity** and total file complexity  

3. **Professional Console Output**
   - Only prints issues if they exist  
   - Clean and readable reports for all checks  

4. **GitHub Actions CI**
   - Automatically compiles all Java files on push  
   - Runs the linter on a sample test file (`ComplexityTest.java`)  
   - Provides a green check if compilation succeeds  

---

## **Demo Output**
Example output when running `CodeChecker` on `ComplexityTest.java`:
```=== CodeChecker ===

Simple Cyclomatic Complexity:
Total Complexity: 10

Advanced Cyclomatic Complexity per method:
Method: main → Complexity: 10
File Total Complexity: 10

No code style issues found!
```

---

## **Getting Started**

### **Prerequisites**

- Java 17+ installed  
- Git installed  
- Command-line access  

---

### **Clone Repository**

```bash
git clone https://github.com/MANISH-K-07/CodeChecker.git
cd CodeChecker
```

## Compile Java Files
```
javac -d out $(find src -name "*.java")
```
- Compiles all `.java` files in `src/`
- Outputs `.class` files to `out/` folder

## Run CodeChecker
```
java -cp out Main src/ComplexityTest.java
```
- `Main.java` is the entry point
- Replace `ComplexityTest.java` with any Java file you want to analyze

## Run GitHub Actions CI

- The workflow automatically runs on every push to main
- Compiles all Java files and runs CodeChecker on the test file
- Console output is visible in the Actions tab on GitHub

## Folder Structure
```
CodeChecker/
│
├─ src/
│   ├─ Main.java
│   ├─ IndentationChecker.java
│   ├─ TodoChecker.java
│   ├─ TrailingWhitespaceChecker.java
│   ├─ SimpleCyclomaticComplexityChecker.java
│   ├─ AdvancedCyclomaticComplexityChecker.java
│   ├─ ComplexityTest.java
│
├─ .github/
│   └─ workflows/
│       └─ java-ci.yml
```
## Tech Stack
- Java 17
- GitHub Actions (CI/CD)
- Standard Java libraries (no external dependencies)

## Portfolio

This project demonstrates:
- Practical knowledge of Java and software engineering principles
- Understanding of code quality metrics and complexity analysis
- Ability to create professional, automated, and well-documented projects for GitHub portfolios

