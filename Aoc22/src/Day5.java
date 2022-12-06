import java.io.File;
import java.util.Scanner;

public class Day5 {

    // outputs the reflection a string
    public static String reverseString(String str) {
        String out = "";
        int k = str.length();
        for(int i = 0; i < k; i++) {
            out += str.substring(k-i-1, k-i);
        }
        return out; 
    }

    // transforms move inputs into an int[] for easier access
    public static int[] moveInput(String str) {
        String temp = str;

        // remove extra words to be left with only digits in the line
        temp = temp.replace("move ","");
        temp = temp.replace(" from ","");
        temp = temp.replace(" to ","");
        
        // put those digits in a list
        return new int[] {
            Integer.parseInt(temp.substring(0, temp.length()-2)),
            Integer.parseInt(temp.substring(temp.length()-2, temp.length()-1))-1,
            Integer.parseInt(temp.substring(temp.length()-1, temp.length()))-1
        };
    }

    // updates the crate configuration affected by a move
    public static void makeMove(String[] crates, int[] move) {
        crates[move[2]] = reverseString(crates[move[1]].substring(0,move[0]))+crates[move[2]];
        crates[move[1]] = crates[move[1]].substring(move[0],crates[move[1]].length());
    }

    public static void main(String[] args) throws Exception {
        // setup

        File file = new File("src/inputs/5.txt");
        Scanner scan = new Scanner(file);

        String[] thing = new String[] {"","","","","","","","",""};
        int iterator = 0;

        // transform crates from input file to strings
        while(iterator < 8) {
            String line = scan.nextLine();
            
            for(int i = 0; i < 9; i++) {
                if(line.charAt(4*i + 1) != ' ') {
                    thing[i] += line.charAt(4*i+1);
                }
            }
            iterator++;
        }

        // skipping lines 9-10 from input file
        scan.nextLine();
        scan.nextLine();

        // affecting the crates with the perscribed moves
        while(scan.hasNextLine()) {
            makeMove(thing, moveInput(scan.nextLine()));
        }

        // part 1
        String res = "";
        
        for(String st : thing) {
            res += st.substring(0,1);
        }
        System.out.println(res);

        // part 2 is obtained by removing the reverseString() function on line 36

        scan.close();
    }    
}
