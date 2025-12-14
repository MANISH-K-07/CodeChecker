import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdvancedCyclomaticComplexityChecker {

    /**
     * Result class to hold per-method and total cyclomatic complexity
     */
    public static class Result {
        public final Map<String, Integer> methodComplexity;
        public final int totalComplexity;

        public Result(Map<String, Integer> methodComplexity, int totalComplexity) {
            this.methodComplexity = methodComplexity;
            this.totalComplexity = totalComplexity;
        }
    }

    /**
     * Analyze a Java file and calculate cyclomatic complexity per method and total.
     * @param filePath Path to the Java source file
     * @return Result object containing per-method complexity map and total complexity
     */
    public static Result analyze(String filePath) {
        Map<String, Integer> methodComplexity = new LinkedHashMap<>();
        int totalComplexity = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentMethod = null;
            int braceDepth = 0;
            int counter = 0;

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
                    counter = 1;    // base complexity
                    continue;
                }

                // Update brace depth
                braceDepth += count(trimmed, "{");
                braceDepth -= count(trimmed, "}");

                if (currentMethod != null) {
                    // Count cyclomatic complexity decision points
                    if (trimmed.matches("^if\\b.*")) counter++;
                    if (trimmed.matches("^for\\b.*")) counter++;
                    if (trimmed.matches("^while\\b.*")) counter++;
                    if (trimmed.matches(".*\\bcatch\\b.*")) counter++;
                    if (trimmed.matches("^case\\b.*")) counter++;
                    counter += count(trimmed, "&&");
                    counter += count(trimmed, "||");

                    // End of method
                    if (braceDepth == 0) {
                        methodComplexity.put(currentMethod, counter);
                        totalComplexity += counter;
                        currentMethod = null;
                        counter = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return new Result(methodComplexity, totalComplexity);
    }

    /**
     * Extract method name from a declaration line
     */
    private static String extractMethodName(String line) {
        line = line.replaceAll("(public|private|protected|static|final|synchronized|abstract)\\s+", "");
        line = line.replaceAll("\\s*\\(.*", ""); // remove parameters
        String[] parts = line.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    /**
     * Count occurrences of a token in a line
     */
    private static int count(String str, String token) {
        int c = 0, i = 0;
        while ((i = str.indexOf(token, i)) != -1) {
            c++;
            i += token.length();
        }
        return c;
    }
}
