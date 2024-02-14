import java.util.*;
/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */
public class CA3_Question7 {
    private static final Map<String, Queue<Block>> stockMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command;

        do {
            System.out.print(">");
            command = in.next();

            if (command.equalsIgnoreCase("buy")) {
                String company = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();
                buyShares(company, qty, price);
            } else if (command.equalsIgnoreCase("sell")) {
                String company = in.next();
                int qty = in.nextInt();
                sellShares(company, qty);
            }
        } while (!command.equalsIgnoreCase("quit"));
        in.close();
    }

    private static void buyShares(String company, int quantity, double price) {
        stockMap.putIfAbsent(company, new LinkedList<>());
        stockMap.get(company).offer(new Block(quantity, price));
    }

    private static void sellShares(String company, int quantity) {
        if (!stockMap.containsKey(company) || stockMap.get(company).isEmpty()) {
            System.out.println("No shares available for " + company);
            return;
        }

        Queue<Block> blocks = stockMap.get(company);
        double totalGain = 0.0;
        int totalSold = 0;

        while (quantity > 0 && !blocks.isEmpty()) {
            Block currentBlock = blocks.peek();
            int sharesToSell = Math.min(quantity, currentBlock.quantity);

            totalGain += (sharesToSell * (15 - currentBlock.price));
            currentBlock.quantity -= sharesToSell;
            totalSold += sharesToSell;
            quantity -= sharesToSell;

            if (currentBlock.quantity == 0) {
                blocks.poll();
            }
        }

        if (totalSold > 0) {
            System.out.println("Total gain from selling shares of " + company + ": $" + totalGain);
        }
        if (quantity > 0) {
            System.out.println("Not enough shares of " + company + " to sell. " + quantity + " shares unsold.");
        }
    }
}
