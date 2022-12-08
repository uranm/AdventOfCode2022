import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day8 {

    // counts indices in a string which hold a visible value
    static Set sideVisibility(int row, String string) {
        int k = string.length();
        int leftHigh = 0;
        int rightHigh = string.length()-1;

        Set visibleIndices = new HashSet();
        visibleIndices.add(row + "-" + 0);
        visibleIndices.add(row + "-" + (k-1));

        for(int i = 0; i < k; i++) {
            if (string.charAt(leftHigh) < string.charAt(i)) {
                leftHigh = i;
                visibleIndices.add(row + "-" + i);
            }
            if (string.charAt(rightHigh) < string.charAt(k-1-i)) {
                rightHigh = k-1-i;
                visibleIndices.add(row + "-" + (k-1-i));
            }
        }
        return visibleIndices;
    }

    // useful when we check visible indices on columns
    static String flipCoordinate(String coord) {
        String[] crd = coord.split("[-]");

        return crd[1]+"-"+crd[0];
    }

    // part 2
    static int visibilityRange(int i, String s) {
        if (i == 0 || i == s.length()-1) {
            return 0;
        }
        int k = s.length();
        int res1 = 0;
        int res2 = 0;

        for(int j = i + 1; j < k; j++) {
            if (s.charAt(j) < s.charAt(i)) {
                res1 += 1;
            }
            else {
                res1 += 1;
                break;
            }
        }

        for(int j = i - 1; 0 <= j; j--) {
            if (s.charAt(j) < s.charAt(i)) {
                res2 += 1;
            }
            else {
                res2 += 1;
                break;
            }
        }
        return res1*res2;
    }

    public static void main(String[] args) throws Exception{
        
        // setup

        File file = new File("src/inputs/8.txt");
        Scanner scan = new Scanner(file);

        Set visibleCoordinates = new HashSet();

        // populate hashSet with side-visible coordinates
        for(int i = 0; i < 99; i++) {
            for(Object x : sideVisibility(i, scan.nextLine())) {
                visibleCoordinates.add(x);
            }
        }

        // store columns into a list of strings
        scan = new Scanner(file);

        String[] columns = new String[99];

        for(int i = 0; i < 99; i++) {
            columns[i] = "";
        }

        while(scan.hasNext()) {
            String s = scan.next();

            for(int j = 0; j < 99; j++) {
                columns[j] += s.charAt(j);
            }
        }

        // populate hashSet with vertically-visible coordinates
        for(int j = 0; j < 99; j++) {
            for(Object x : sideVisibility(j, columns[j])) {
                visibleCoordinates.add(flipCoordinate(x.toString()));
            }           
        }

        // part 1
        System.out.println(visibleCoordinates.size());

        scan = new Scanner(file);
        int res = 0;

        // part 2
        
        for(int i = 0; i < 99; i++) {
            String s = scan.next();
            for(int j = 0; j < 99; j++) {
                int temp_res = visibilityRange(j, s)*visibilityRange(i, columns[j]);
                res = Math.max(res,temp_res);
                }
            }

        System.out.println(res);

        scan.close();
        }
    }
