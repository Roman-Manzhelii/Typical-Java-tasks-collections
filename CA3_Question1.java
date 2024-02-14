/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */
import java.util.Scanner;
import java.util.Stack;

public class CA3_Question1 {

    public static void runSimulation() {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> driveway = new Stack<>();
        Stack<Integer> street = new Stack<>();
        int carNumber;

        while (true) {
            System.out.print("Enter a car number (positive to park, negative to retrieve, zero to stop): ");
            carNumber = scanner.nextInt();

            if (carNumber == 0) {
                break;
            }

            if (carNumber > 0) {
                // Parking a car
                driveway.push(carNumber);
                System.out.println("Car " + carNumber + " parked in the driveway.");
            } else {
                // Retrieving a car
                carNumber = -carNumber;
                if (driveway.contains(carNumber)) {
                    while (driveway.peek() != carNumber) {
                        street.push(driveway.pop());
                    }

                    driveway.pop();
                    System.out.println("Car " + carNumber + " retrieved from the driveway.");

                    //TODO Uncomment next code to Move cars back from street to driveway
//                    while (!street.isEmpty()) {
//                        driveway.push(street.pop());
//                    }
                } else {
                    System.out.println("Car " + carNumber + " is not in the driveway.");
                }
            }

            System.out.println("Driveway: " + driveway);
            System.out.println("Street: " + street);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
