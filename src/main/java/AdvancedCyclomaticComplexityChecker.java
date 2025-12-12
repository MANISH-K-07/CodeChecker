import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdvancedCyclomaticComplexityChecker {

    /**
     * Calculates method-level cyclomatic complexity and prints details.
     * Returns total file complexity as int.
     */
    public static int calculate(String filePath) {
        Map<String, Integer> methodComplexity = new LinkedHashMap<>();
        int totalComplexity = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentMethod = null;
            int braceDepth = 0;
            int methodComplexityCounter = 0;

            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();

                // Remove inline comments
                int commentIndex = trimmed.indexOf("//");
                if (commentIndex != -1) {
                    trimmed = trimmed.substring(0, commentIndex).trim();
                }

                if (trimmed.isEmpty()) continue;

                // Detect method declaration
                if (trimmed.matches(".*\\b(public|private|protected)?\\s*(static)?\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{.*")) {
                    currentMethod = extractMethodName(trimmed);
                    braceDepth = 1; // opening brace of method
                    methodComplexityCounter = 1; // base complexity
                    continue;
                }

                // Update brace depth
                braceDepth += countOccurrences(trimmed, "{");
                braceDepth -= countOccurrences(trimmed, "}");

                // Count cyclomatic complexity inside a method
                if (currentMethod != null) {
                    if (trimmed.matches("^if\\b.*")) methodComplexityCounter++;
                    if (trimmed.matches("^for\\b.*")) methodComplexityCounter++;
                    if (trimmed.matches("^while\\b.*")) methodComplexityCounter++;
                    if (trimmed.matches(".*\\bcatch\\b.*")) methodComplexityCounter++;
                    if (trimmed.matches("^case\\b.*")) methodComplexityCounter++;
                    methodComplexityCounter += countOccurrences(trimmed, "&&");
                    methodComplexityCounter += countOccurrences(trimmed, "||");

                    // If method ends
                    if (braceDepth == 0) {
                        methodComplexity.put(currentMethod, methodComplexityCounter);
                        totalComplexity += methodComplexityCounter;
                        currentMethod = null;
                        methodComplexityCounter = 0;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return 0;
        }

        // Print results only if methods exist
        if (!methodComplexity.isEmpty()) {
            System.out.println("Advanced Cyclomatic Complexity per method:");
            for (Map.Entry<String, Integer> entry : methodComplexity.entrySet()) {
                System.out.println("Method: " + entry.getKey() + " - Complexity: " + entry.getValue());
            }
            System.out.println("File Total Complexity: " + totalComplexity);
        }

        return totalComplexity;
    }

    // Helper to extract method name from signature
    private static String extractMethodName(String line) {
        // Remove modifiers and return type
        line = line.replaceAll("(public|private|protected|static|final|synchronized|abstract)\\s+", "");
        line = line.replaceAll("\\s*\\(.*", ""); // remove parameters
        String[] parts = line.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    // Helper to count occurrences of a token in a line
    private static int countOccurrences(String str, String token) {
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(token, index)) != -1) {
            count++;
            index += token.length();
        }
        return count;
    }
}
