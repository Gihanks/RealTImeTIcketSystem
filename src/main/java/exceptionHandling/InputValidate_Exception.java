package exceptionHandling;

import java.util.Scanner;

public class InputValidate_Exception extends Exception {

    public InputValidate_Exception(String message) {
        super(message);
    }

    // Static method to validate integer input
    public static int Positiveinputvalidation(Scanner scanner, String prompt) throws InputValidate_Exception {
        System.out.print(prompt);
        if (!scanner.hasNextInt()) {
            scanner.next(); // Consume invalid input
            throw new InputValidate_Exception("Invalid input. Please enter a positive integer.");
        }

        int value = scanner.nextInt();
        if (value <= 0) {
            throw new InputValidate_Exception("Input must be a positive integer greater than zero.");
        }

        return value;
    }
}
