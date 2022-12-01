import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // DAY 1 //
        
        // SETUP
        File file = new File("src/1a.txt");
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

            // accumulate elf_calories til you encounter an empty line
            if (s.length() > 0) {
                elf_calories += Integer.parseInt(s);
            }

            // once you arrive at an empty line, see if the elf_calories makes the podium
            if (s.length() == 0) {
                if (elf_calories > top3.get(0))
                {
                    top3.remove(0);
                    top3.add(elf_calories);
                }
                
            // get ready to count next elf's calories
                elf_calories = 0;
            }
        }
        
        // result
        System.out.println(top3.get(0) + top3.get(1) + top3.get(2));
        scan.close();
    }
}
