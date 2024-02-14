import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */

public class CA3_Question3 {
    public static void readFile(String fileName) throws FileNotFoundException {

        Map<String, List<Integer>> identifierMap = new HashMap<>();
        Scanner fileScanner = new Scanner(new File(fileName));

        int lineNumber = 0;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            lineNumber++;

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("[^A-Za-z0-9_]+");

            while (lineScanner.hasNext()) {
                String identifier = lineScanner.next();

                if (!identifierMap.containsKey(identifier)) {
                    identifierMap.put(identifier, new ArrayList<>());
                }
                identifierMap.get(identifier).add(lineNumber);
            }

            lineScanner.close();
        }

        fileScanner.close();

        for (Map.Entry<String, List<Integer>> entry : identifierMap.entrySet()) {
            System.out.println("Identifier: " + entry.getKey() + " - Lines: " + entry.getValue());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/CA3_Question1.java");
    }
}
