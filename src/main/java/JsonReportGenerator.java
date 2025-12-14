import java.util.Map;

public class JsonReportGenerator {

    public static String generate(Map<String, Object> data) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");

        int count = 0;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            json.append("  \"")
                .append(entry.getKey())
                .append("\": ")
                .append(entry.getValue().toString());

            count++;
            if (count < data.size()) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("}");
        return json.toString();
    }
}
