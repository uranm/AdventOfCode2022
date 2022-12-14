import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {

    static class Monkey {
        private int id;
        private ArrayList<Long> items;
        private String[] operationDetails;
        private int quant;
        private int[] throwTo;

        public long operation(long x) {
            String[] opDeets = this.operationDetails;
            long y = 0;
            String operand = opDeets[0];
            String otherNumber = opDeets[1];
            
            if (otherNumber.equals("old")) {
                y = x;
            } else {
                y = Integer.parseInt(otherNumber);
            }

            long res = 0;
            if (operand.equals("*")) {
                res = x * y;
            }
            else {
                res = x + y;
            }
            return res;
        }

        public int divTest(long item, int quant) {
            int res = 1;
            if (item % quant == 0) {
                res--;
            }
            return res;
        }

        Monkey(int id, ArrayList<Long> items, String[] operationDetails, int quant, int[] throwTo) {
            this.id = id;
            this.items = items;
            this.operationDetails = operationDetails;
            this.quant = quant;
            this.throwTo = throwTo;
        }
    }
    public static void main(String[] args) throws Exception {
        // setup
        File file = new File("src/inputs/11.txt");
        Scanner scan = new Scanner(file);

        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
        
        // Creating an instance Monkey class from input
        int id = 0;
        while(scan.hasNextLine() && scan.nextLine().charAt(0) == 'M'){

            // Parse items held by monke
            String s = scan.nextLine().substring(18);
            s = s.replaceAll("[,]","");

            String[] itemsString = s.split(" ");
            ArrayList<Long> items = new ArrayList<>();

            for(String t : itemsString) {
                items.add((long) Integer.parseInt(t));
            }

            // Operation details
            s = scan.nextLine().substring(23);
            String[] operationDetails = s.split(" ");

            // Test quantity
            int quant = Integer.parseInt(scan.nextLine().substring(21));

            // Which monkeys to throw to
            int[] throwTo = new int[] {Integer.parseInt(scan.nextLine().substring(29)),
                                        Integer.parseInt(scan.nextLine().substring(30))};
            
            Monkey monkey = new Monkey(id, items, operationDetails, quant, throwTo);
            monkeys.add(monkey);
            id++;

            // end the scan
            if (scan.hasNextLine()) {
                scan.nextLine();
                }
                }
        scan.close();
            
            int reasonableQuantity = 1;

            for(Monkey m : monkeys) {
                reasonableQuantity *= m.quant;
            }
            int round = 0;
            
            Long[] inspectedElements = new Long[monkeys.size()];
            
            for(Monkey m : monkeys) {
                inspectedElements[m.id] = 0L;
            }

            while(round < 10000) {
            for(Monkey m : monkeys) {
                inspectedElements[m.id] += (long) m.items.size();
                

                for(long item : m.items) {

                    long newitem = m.operation(item);
                    newitem %= reasonableQuantity;
                    // for part 1
                    // newitem /= 3;

                    int newindex = m.throwTo[m.divTest(newitem, m.quant)];

                    monkeys.get(newindex).items.add(newitem);
                }
                m.items.clear();
                
            }
            round++;
            }

            Long res = 0L;
            for(int i = 0; i < monkeys.size(); i++) {
                for(int j = i+1; j < monkeys.size();j++) {
                    res = Math.max(res,inspectedElements[i]*inspectedElements[j]);
                }
            }

            System.out.println("Monkey Business score: " + res);

    }
}
