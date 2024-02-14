import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */

public class CA3_Question5 {
    private static final Queue<String> takeoffQueue = new LinkedList<>();
    private static final Queue<String> landingQueue = new LinkedList<>();


    public static void takeoff(String flightSymbol) {
        takeoffQueue.offer(flightSymbol);
        System.out.println("Flight " + flightSymbol + " is queued for takeoff.");
    }

    public static void land(String flightSymbol) {
        landingQueue.offer(flightSymbol);
        System.out.println("Flight " + flightSymbol + " is queued for landing.");
    }

    public static void next() {
        if (!landingQueue.isEmpty()) {
            System.out.println("Flight " + landingQueue.poll() + " is landing.");
        } else if (!takeoffQueue.isEmpty()) {
            System.out.println("Flight " + takeoffQueue.poll() + " is taking off.");
        } else {
            System.out.println("No flights are waiting.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter command (takeoff, land, next, quit):");
            command = scanner.nextLine();
            String[] parts = command.split("\\s+");

            if (parts[0].equalsIgnoreCase("quit")) {
                break;
            } else if (parts[0].equalsIgnoreCase("takeoff")) {
                if (parts.length < 2) {
                    System.out.println("Please specify a flight symbol for takeoff.");
                } else {
                    takeoff(parts[1]);
                }
            } else if (parts[0].equalsIgnoreCase("land")) {
                if (parts.length < 2) {
                    System.out.println("Please specify a flight symbol for landing.");
                } else {
                    land(parts[1]);
                }
            } else if (parts[0].equalsIgnoreCase("next")) {
                next();
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }

}
