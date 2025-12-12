import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleCyclomaticComplexityChecker {

    public static int calculate(String filePath) {
        int complexity = 1; // base complexity

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                String trimmed = line.trim();

                // Remove inline comments
                int commentIndex = trimmed.indexOf("//");
                if (commentIndex != -1) {
                    trimmed = trimmed.substring(0, commentIndex).trim();
                }

                // Skip empty lines
                if (trimmed.isEmpty()) continue;

                // Detect control-flow keywords
                if (trimmed.matches("^if\\b.*")) complexity++;
                if (trimmed.matches("^for\\b.*")) complexity++;
                if (trimmed.matches("^while\\b.*")) complexity++;
                if (trimmed.matches(".*\\bcatch\\b.*")) complexity++; // fixed catch detection
                if (trimmed.matches("^case\\b.*")) complexity++;

                // Count logical operators
                complexity += countOccurrences(trimmed, "&&");
                complexity += countOccurrences(trimmed, "||");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return complexity;
    }

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
