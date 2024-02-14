import java.util.Scanner;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */
public class CA3_Question8 {

    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation:");
        equation = in.nextLine().trim();

        String rpn = convertToRPN(equation);    //Reverse Polish Notation
        System.out.println("RPN Expression: " + rpn);
        double result = evaluateRPN(rpn);
        System.out.println("Result: " + result);
    }

    private static String convertToRPN(String infix) {
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);

        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(' ').append(stack.pop());
                }
                stack.pop(); // Remove '('
            } else if (precedence.containsKey(c)) {
                while (!stack.isEmpty() && precedence.getOrDefault(stack.peek(), 0) >= precedence.get(c)) {
                    output.append(' ').append(stack.pop());
                }
                output.append(' ');
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            output.append(' ').append(stack.pop());
        }

        return output.toString();
    }

    private static double evaluateRPN(String rpn) {
        Stack<Double> stack = new Stack<>();
        for (String token : rpn.split("\\s+")) {
            if (token.isEmpty()) continue;
            char c = token.charAt(0);
            if (Character.isDigit(c) || token.length() > 1) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (c) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }
}
