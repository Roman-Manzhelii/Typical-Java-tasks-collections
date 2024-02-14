import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name: Roman Manzhelii
 *  Class Group: Sd2a
 */
class Block {
    int quantity;
    double price;

    public Block(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}

public class CA3_Question6 {
    private static final Queue<Block> stockQueue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command;

        do {
            System.out.print(">");
            command = in.next();

            if (command.equalsIgnoreCase("buy")) {
                int qty = in.nextInt();
                double price = in.nextDouble();
                buyShares(qty, price);
            } else if (command.equalsIgnoreCase("sell")) {
                int qty = in.nextInt();
                sellShares(qty);
            }
        } while (!command.equalsIgnoreCase("quit"));
        in.close();
    }

    private static void buyShares(int quantity, double price) {
        stockQueue.offer(new Block(quantity, price));
    }

    private static void sellShares(int quantity) {
        double totalGain = 0.0;
        int totalSold = 0;

        while (quantity > 0 && !stockQueue.isEmpty()) {
            Block currentBlock = stockQueue.peek();
            int sharesToSell = Math.min(quantity, currentBlock.quantity);

            totalGain += (sharesToSell * (15 - currentBlock.price));
            currentBlock.quantity -= sharesToSell;
            totalSold += sharesToSell;
            quantity -= sharesToSell;

            if (currentBlock.quantity == 0) {
                stockQueue.poll();
            }
        }

        if (totalSold > 0) {
            System.out.println("Total gain from sale: $" + totalGain);
        }

        if (quantity > 0) {
            System.out.println("Not enough shares to sell. " + quantity + " shares unsold.");
        }
    }
}
