import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndentationChecker {

    public static List<String> checkIndentation(String filePath) {
        List<String> issues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {

                // Ignore empty lines
                if (line.trim().isEmpty()) {
                    lineNumber++;
                    continue;
                }

                // Count leading spaces
                int spaces = 0;
                while (spaces < line.length() && line.charAt(spaces) == ' ') {
                    spaces++;
                }

                // If line starts with a tab -> invalid
                if (line.startsWith("\t")) {
                    issues.add("Indentation error (TAB) at line " + lineNumber);
                }

                // If spaces are not a multiple of 4 -> invalid
                else if (spaces % 4 != 0) {
                    issues.add("Indentation error (not multiple of 4 spaces) at line " + lineNumber);
                }

                lineNumber++;
            }

        } catch (IOException e) {
            issues.add("Error reading file: " + e.getMessage());
        }

        return issues;
    }
}
