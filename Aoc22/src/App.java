import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        // SETUP
        File file = new File("src/1a.txt");
        Scanner scan = new Scanner(file);
        int sum= 0;

        List<Integer> top3 = new ArrayList<Integer>();

        top3.add(0);
        top3.add(0);
        top3.add(0);

        // for(int x : top3) {
        //     System.out.println(x);
        // }

        while(scan.hasNextLine()) {
            Collections.sort(top3);

            String s = scan.nextLine();
            if (s.length() > 0) {
                sum += Integer.parseInt(s);
            }
            if (s.length() == 0) {
                for(int i = 0; i < 3; i++){
                    if (sum > top3.get(i)) {
                        top3.remove(i);
                        top3.add(sum);
                        break;
                    }
                }
                sum = 0;
            }
        }
        
        System.out.println(top3.get(0) + top3.get(1) + top3.get(2));
        // System.out.println(top3[0] + " " + top3[1] + " " + top3[2]);
        scan.close();
    }
}
