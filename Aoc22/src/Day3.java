import java.io.File;
import java.util.Scanner;

public class Day3 {

    // Find common letter of three strings
    public static int findBadgePriority(String first, String second, String third) {
        int priority = 0;
        for(int i = 0; i < first.length(); i++) {
            for(int j = 0; j < second.length(); j++) {
                for(int k = 0; k < third.length(); k++) {
                    if (first.charAt(i) == second.charAt(j) & second.charAt(j) == third.charAt(k)) {
                        priority = letterPriority(third.charAt(k));
                    }
                }
            }
        }
        return priority;
    }

    // Computes the priority of a letter appearing in each half of a string
    public static int priorityOfCommonLetter(String string) {

        int half = string.length() / 2;
        int res = 0;

        for(int i = 0; i < half; i++) {
            for(int j = 0; j < half; j++) {
                if (string.charAt(i) == string.charAt(j+half)) {
                    res = letterPriority(string.charAt(i));
                    break;
                }
            }
        }
        return res;
    }

    // Get letter priority using their order in ASCII
    public static int letterPriority(char letter) {

        int position = 0;

        // scanning the ASCII characters, and shifting appropriately
        if ((int) letter < 91) {
            position = (int) letter - 38;
        }
        else {
            position = (int) letter - 96;
        }
        return position;
    }
    
    public static void main(String[] args) throws Exception {

        // setup
        File file = new File("src/inputs/3.txt");
        Scanner scan = new Scanner(file);


        // part 1
        int resPart1 = 0;
        while (scan.hasNextLine()) {
            resPart1 += priorityOfCommonLetter(scan.next());
        }

        scan.close();
        System.out.println(resPart1);

        // part 2

        scan = new Scanner(file);
        int resPart2 = 0;
        while (scan.hasNextLine()) {
            resPart2 += findBadgePriority(scan.next(),scan.next(),scan.next());        
        }

        System.out.println(resPart2);

        scan.close();
    }
    
}
