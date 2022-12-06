import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day6 {

    public static void main(String[] args) throws Exception {
    // setup
    File file = new File("src/inputs/6.txt");
    Scanner scan = new Scanner(file);
    
    String input = scan.next();

    int markerSize = 4; // for part 1; while = 14 for part 2
    int res = 0;

    int k = input.length() - markerSize;

    for(int i = 0; i < k; i++) {
        Set<Character> consecutiveCharacters = new HashSet<Character>();

        for(int j = 0; j < markerSize; j++) {
        consecutiveCharacters.add(input.charAt(i+j));
        }
        
        if (consecutiveCharacters.size() == markerSize) {
            res = i + markerSize;
            System.out.println(consecutiveCharacters);

            break;
        }
    }
    // part 1 with markersize = 4. For part 2, set markerSize = 14

    System.out.println(res);

    scan.close();
    }
    
}
