import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrailingWhitespaceChecker {

    public static List<String> checkTrailingWhitespace(String filePath) {
        List<String> issues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {

                if (line.matches(".*\\s+$")) {
                    issues.add("Trailing whitespace at line " + lineNumber);
                }

                lineNumber++;
            }

        } catch (IOException e) {
            issues.add("Error reading file: " + e.getMessage());
        }

        return issues;
    }
}
