import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class Day10 {

    public static String drawPixel(int cycle, int spritePosition, String row) {
        if ((spritePosition -1 <= cycle) && (spritePosition + 1 >= cycle)) {
            row += "#";
        } else {
            row += ".";
        }
        return row;
    }
    public static void main(String[] args) throws Exception {
        // setup
        File file = new File("src/inputs/10.txt");
        Scanner scan = new Scanner(file);

        // part 1
        int res = 1;
        List<Integer> signals = new ArrayList<>();

        while(scan.hasNextLine()) {
            String s = scan.nextLine();

            if (s.charAt(0) == 'n') {
                signals.add(res);
            }
            if (s.charAt(0) == 'a') {
                signals.add(res);
                int x = Integer.parseInt(s.substring(5));
                res += x;
                signals.add(res);
            }
        }

        scan.close();

        int resPart1 = 0;
        System.out.println(signals.get(218));
        for(int i = 20; i <= 220; i = i + 40) {
            resPart1 += (signals.get(i-2)*i);
        }

        System.out.println(resPart1);
        
        // part 2   

        for(int j = 0; j < 6; j++) {
            String r = "";
            r = drawPixel(1,signals.get(0),r);

            for(int i = 1 + 40*j; i < 40*(j+1); i++) {
                r = drawPixel(i-40*j,signals.get(i-1),r);
            }
        System.out.println(r);

    }
}
}
