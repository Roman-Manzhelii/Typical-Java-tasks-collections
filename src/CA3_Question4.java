import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */
public class CA3_Question4 {
    public static boolean validate(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        Stack<String> tagStack = new Stack<>();

        while (scanner.hasNext()) {
            String tag = scanner.next();

            if (tag.startsWith("<") && !tag.startsWith("</")) {
                // Opening tag
                tagStack.push(tag);
            } else if (tag.startsWith("</")) {
                // Closing tag
                if (tagStack.isEmpty()) {
                    return false;
                }

                String openingTag = tagStack.pop();
                // Convert opening tag to closing tag
                String expectedClosingTag = openingTag.charAt(0) + "/" + openingTag.substring(1);

                if (!tag.equals(expectedClosingTag)) {
                    return false;
                }
            }
        }

        return tagStack.isEmpty(); //true
    }


    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for(String fName: files) {
            System.out.print(fName +": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }
}
