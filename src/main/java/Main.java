import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java Main <filepath> [--json]");
            return;
        }

        String filePath = args[0];
        boolean jsonOutput = false;

        // Check for JSON flag
        for (String arg : args) {
            if (arg.equals("--json")) {
                jsonOutput = true;
            }
        }

        // ---------------------------------------------------
        // Run all checks (analysis phase)
        // ---------------------------------------------------
        List<String> indentationIssues = IndentationChecker.checkIndentation(filePath);
        List<String> todoIssues = TodoChecker.checkTodos(filePath);
        List<String> whitespaceIssues = TrailingWhitespaceChecker.checkTrailingWhitespace(filePath);

        // Cyclomatic complexity is always calculated but not considered an "issue"
        int simpleComplexity = SimpleCyclomaticComplexityChecker.calculate(filePath);

        // Use the analyze() method for advanced complexity
        AdvancedCyclomaticComplexityChecker.Result advancedResult =
                AdvancedCyclomaticComplexityChecker.analyze(filePath);
        int advancedComplexity = advancedResult.totalComplexity;

        // anyIssues only tracks real code style issues (not complexity)
        boolean anyIssues = !indentationIssues.isEmpty() || !todoIssues.isEmpty() || !whitespaceIssues.isEmpty();

        // ---------------------------------------------------
        // JSON OUTPUT MODE
        // ---------------------------------------------------
        if (jsonOutput) {
            Map<String, Object> report = new LinkedHashMap<>(); // preserves order
            report.put("file", filePath);
            report.put("indentationIssues", indentationIssues);
            report.put("todoIssues", todoIssues);
            report.put("trailingWhitespaceIssues", whitespaceIssues);
            report.put("simpleCyclomaticComplexity", simpleComplexity);
            report.put("advancedCyclomaticComplexityTotal", advancedComplexity);
            report.put("advancedCyclomaticComplexityPerMethod", advancedResult.methodComplexity);
            report.put("anyIssues", anyIssues);

            System.out.println(JsonReportGenerator.generate(report));
            return;
        }

        // ---------------------------------------------------
        // NORMAL CONSOLE OUTPUT
        // ---------------------------------------------------
        System.out.println("=== CodeChecker ===\n");

        if (!indentationIssues.isEmpty()) {
            System.out.println("Indentation Issues:");
            for (String issue : indentationIssues) {
                System.out.println("  " + issue);
            }
            System.out.println();
        }

        if (!todoIssues.isEmpty()) {
            System.out.println("TODO/FIXME Issues:");
            for (String issue : todoIssues) {
                System.out.println("  " + issue);
            }
            System.out.println();
        }

        if (!whitespaceIssues.isEmpty()) {
            System.out.println("Trailing Whitespace Issues:");
            for (String issue : whitespaceIssues) {
                System.out.println("  " + issue);
            }
            System.out.println();
        }

        // Always print Cyclomatic Complexity
        if (simpleComplexity > 0) {
            System.out.println("Simple Cyclomatic Complexity:");
            System.out.println("Total Complexity: " + simpleComplexity + "\n");
        }

        if (advancedComplexity > 0) {
            System.out.println("Advanced Cyclomatic Complexity per method:");
            for (Map.Entry<String, Integer> entry : advancedResult.methodComplexity.entrySet()) {
                System.out.println("Method: " + entry.getKey() + " - Complexity: " + entry.getValue());
            }
            System.out.println("File Total Complexity: " + advancedComplexity + "\n");
        }

        if (!anyIssues) {
            System.out.println("No code style issues found!");
        }
    }
}
