import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day09 {

    // tranform input into coordinates of the form x_y, and add them to a list allCoords
    public static void inputToCoordinates(String move, String current, List<String> allCoords) {
        char direction = move.charAt(0);
        int size = Integer.parseInt(move.substring(2));

        String[] coordinates = current.split("[_]");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        
        for(int i = 1; i <= size; i++) {
            String res = "";
        switch(direction) {
            case 'R':
                res = (x+i) + "_" + y;
                break;
            case 'L':
                res = (x - i) + "_" + y;
                break;
            case 'U':
                res = x + "_" + (y + i);
                break;
            case 'D':
                res = x + "_" + (y-i);
                break;
        }

        allCoords.add(res);
    }
    }

    // for two neighboring points, when one moves one step, the neighbor follows along these instructions
    public static String closestNeighbor(String currentH, String currentT) {
        int Hx = Integer.parseInt(currentH.split("[_]")[0]);
        int Hy = Integer.parseInt(currentH.split("[_]")[1]);

        String result = "0_0";

        if (distance(currentH,currentT) <= 1) {
            return currentT;
        } else {
        String[] neighbors = new String[] {(Hx+1) + "_" + Hy,
                                        (Hx-1) + "_" + Hy,
                                        Hx + "_" + (Hy+1),
                                        Hx + "_" + (Hy-1),
                                        (Hx+1 )+ "_" + (Hy+1),
                                        (Hx+1) + "_" + (Hy-1),
                                        (Hx-1) + "_" + (Hy-1),
                                        (Hx-1)+ "_" + (Hy+1)};

        for(String x : neighbors) {
            if (distance(x,currentT) == 1) {
                result = x;
                break;
            }
        }
        return result;
    }
    }

    // ugly manhattan distance
    public static int distance(String coords1, String coords2) {
        int x1 = Integer.parseInt(coords1.split("[_]")[0]);
        int y1 = Integer.parseInt(coords1.split("[_]")[1]);
        int x2 = Integer.parseInt(coords2.split("[_]")[0]);
        int y2 = Integer.parseInt(coords2.split("[_]")[1]);

        return Math.max(Math.abs(x1-x2),Math.abs(y1-y2));
    }

    // recursive function to compute path of a tail
    public static List<String> snakeTail(List<String> snakeHead, int snakeSize) {
        if (snakeSize == 0) {return snakeHead;}

        List<String> temp = new ArrayList<String>();
        temp.add("0_0");

        for (String x : snakeHead) {
            String oldNeck = temp.get(temp.size()-1);
            String newNeck = closestNeighbor(x, oldNeck);

            if (true) {
                temp.add(newNeck);
            }
        }
        return snakeTail(temp, snakeSize-1);
    }
    
    public static void main(String[] args) throws Exception{
        
        // setup

        File file = new File("src/inputs/9.txt");
        Scanner scan = new Scanner(file);

        // will hold coordinates traversed by Head, initialized at origin, and populated via the input
        List<String> coordinatesH = new ArrayList<>();
        coordinatesH.add("0_0");

        while(scan.hasNextLine()) {
            String move = scan.nextLine();
            String currentH = coordinatesH.get(coordinatesH.size() - 1);
            inputToCoordinates(move, currentH, coordinatesH);
        }

        // will be used to count visited spots, removing multiplicities
        Set<String> countingSet = new HashSet<String>();

        
        List<String> targetList;

        // part 1
        targetList = snakeTail(coordinatesH, 1);
        countingSet.addAll(targetList);
        System.out.println(countingSet.size());

        countingSet.clear();

        // part 2
        targetList = snakeTail(coordinatesH, 9);
        countingSet.addAll(targetList);
        System.out.println(countingSet.size());

        scan.close();
        }
    }  
