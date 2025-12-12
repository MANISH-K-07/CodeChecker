public class ComplexityTest {

    public static void main(String[] args) {

        int x = 10;

        if (x > 5 && x < 20) {       // +1 (if) +1 (&&)
            System.out.println("A");
        }

        for (int i = 0; i < 5; i++) {  // +1 (for)
            if (i % 2 == 0 || i == 3) {   // +1 (if) +1 (||)
                System.out.println(i);
            }
        }

        while (x > 0) {              // +1 (while)
            x--;
        }

        try {
            int y = 10 / 0;
        } catch (Exception e) {      // +1 (catch)
            System.out.println("Error");
        }

        switch (x) {
            case 0: System.out.println("Zero"); break;   // +1 (case)
            case 1: System.out.println("One"); break;    // +1 (case)
        }
    }
}
