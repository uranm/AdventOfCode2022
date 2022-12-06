import java.io.File;
import java.util.Scanner;

public class Day2 {

    // Transform the hands played into integers
    public static int[] roundToInt(String round) {
        String possibleHands1 = "ABC";
        String possibleHands2 = "XYZ";

        int hand1 = 0;
        int hand2 = 0;

        for(int i = 0; i < 3; i++) {
            if(round.charAt(0) == possibleHands1.charAt(i)) {
                hand1 = i;
            }
            if (round.charAt(2) == possibleHands2.charAt(i)) {
                hand2 = i;
            }
        }
        return new int[] {hand1,hand2};
    }

    // for part 1: Computing a round score
    public static int roundScore(String round) {

        int diff = roundToInt(round)[1] - roundToInt(round)[0];
        int res = 0;

        // update score depending on the result of the round
        if (diff == 1 || diff == -2) {
            res += 6;
        }
        if (diff == 0) {
            res += 3;
        }

        // add the value of the hand played
        return res + roundToInt(round)[1] + 1;
    }

    // for part 2: Computing a round score
    public static int roundScore2(String round) {

        // score depending on result of the round
        int res = 3*roundToInt(round)[1];

        // compute value of the hand played
        res += ((roundToInt(round)[0] + roundToInt(round)[1]+2) % 3) + 1;

        return res;
    }

    public static void main(String[] args) throws Exception {

        // SETUP
        File file = new File("src/inputs/2.txt");
        Scanner scan = new Scanner(file);

        int resPart1 = 0;
        int resPart2 = 0;

        while(scan.hasNextLine()){
            String s = scan.nextLine();

            resPart1 += roundScore(s);
            resPart2 += roundScore2(s);            
        }
        
        System.out.println(resPart1);
        System.out.println(resPart2);

        scan.close();
    }
    
}