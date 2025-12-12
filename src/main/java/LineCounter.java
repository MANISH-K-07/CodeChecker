import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {

    public static int countLines(String filePath) {
        int lines = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lines;
    }
}
