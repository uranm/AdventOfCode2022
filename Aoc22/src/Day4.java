import java.io.File;
import java.util.Scanner;

public class Day4 {

    // (part1) checks if two intervals [a,b] and [c,d] are nested

    public static boolean checkIfNested(int a, int b, int c, int d) {
        if( (a <= c & d <= b) || (c <= a & b<=d) ) {
            return true;
        } 
        return false;
    }
    
    // (part2) checks if two intervals [a,b] and [c,d] intersect

    public static boolean checkIfIntersect(int a, int b, int c, int d) {
        if ((b < c) || (d < a)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        
        // SETUP
        File file = new File("src/inputs/4.txt");
        Scanner scan = new Scanner(file);

        int resPart1= 0;
        int resPart2 = 0;
        
        while(scan.hasNextLine()) {
            String[] coord = scan.nextLine().split("[,-]");

            if (checkIfNested(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]), 
                    Integer.parseInt(coord[2]), Integer.parseInt(coord[3]))) {
                        resPart1 += 1;
                    }

            if (checkIfIntersect(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]), 
                    Integer.parseInt(coord[2]), Integer.parseInt(coord[3]))) {
                        resPart2 += 1;
                    }

        }

        // part 1
        System.out.println(resPart1);

        // part 2
        System.out.println(resPart2);

        scan.close();
}
}
