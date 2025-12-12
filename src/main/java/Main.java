import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java Main <filepath>");
            return;
        }

        String filePath = args[0];

        System.out.println("=== CodeChecker ===\n");

        boolean anyIssues = false;

        // ---------------------------------------------------
        // 1. Indentation
        // ---------------------------------------------------
        List<String> indentationIssues = IndentationChecker.checkIndentation(filePath);
        if (!indentationIssues.isEmpty()) {
            anyIssues = true;
            System.out.println("Indentation Issues:");
            for (String issue : indentationIssues) {
                System.out.println("  " + issue);
            }
            System.out.println();
        }

        // ---------------------------------------------------
        // 2. TODO/FIXME
        // ---------------------------------------------------
        List<String> todoIssues = TodoChecker.checkTodos(filePath);
        if (!todoIssues.isEmpty()) {
            anyIssues = true;
            System.out.println("TODO/FIXME Issues:");
            for (String issue : todoIssues) {
                System.out.println("  " + issue);
            }
            System.out.println();
        }

        // ---------------------------------------------------
        // 3. Trailing Whitespace
        // ---------------------------------------------------
        List<String> whitespaceIssues = TrailingWhitespaceChecker.checkTrailingWhitespace(filePath);
        if (!whitespaceIssues.isEmpty()) {
            anyIssues = true;
            System.out.println("Trailing Whitespace Issues:");
            for (String issue : whitespaceIssues) {
                System.out.println("  " + issue);
            }
            System.out.println();
        }

        // ---------------------------------------------------
        // 4. Simple Cyclomatic Complexity
        // ---------------------------------------------------
        int simpleComplexity = SimpleCyclomaticComplexityChecker.calculate(filePath);
        if (simpleComplexity > 1) { // base complexity = 1
            anyIssues = true;
            System.out.println("Simple Cyclomatic Complexity:");
            System.out.println("Total Complexity: " + simpleComplexity + "\n");
        }

        // ---------------------------------------------------
        // 5. Advanced Cyclomatic Complexity
        // ---------------------------------------------------
        int advancedTotal = AdvancedCyclomaticComplexityChecker.calculate(filePath); // returns total complexity
        if (advancedTotal > 0) {
            anyIssues = true;
            System.out.println(); // add spacing for readability
        }

        // ---------------------------------------------------
        // Final Status
        // ---------------------------------------------------
        if (!anyIssues) {
            System.out.println("No code style issues found!");
        }
    }
}
