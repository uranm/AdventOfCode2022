import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws Exception {

        // SETUP
        File file = new File("src/inputs/1.txt");
        Scanner scan = new Scanner(file);
        int elf_calories = 0;

        // Podium
        List<Integer> top3 = new ArrayList<Integer>();
        top3.add(0);
        top3.add(0);
        top3.add(0);

        
        while(scan.hasNextLine()) {
            // podium is sorted
            Collections.sort(top3);

            String s = scan.nextLine();

            // add calories of elf til you encounter an empty line
            if (s.length() > 0) {
                elf_calories += Integer.parseInt(s);
            }

            // when you encounter an empty line, see if it enters the podium
            if (s.length() == 0) {
                if (elf_calories > top3.get(0))
                {
                    top3.remove(0);
                    top3.add(elf_calories);
                }
                elf_calories = 0;
            }
        }
        
        // part 1
        System.out.println(top3.get(2));
        // part 2
        System.out.println(top3.get(0) + top3.get(1) + top3.get(2));
        scan.close();
    }
}
