package math_flashcards;

import java.util.Random;
import java.util.Scanner;

public class Group14_Project1 {

    public static void main(String[] args) {
        
        // Declaring and instantiating objects
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Prompting for user's name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        char operation = ' ';
        String longProbType = "";
        
        // Loop until valid operation is entered
        while (true) {

            // Prompting user to select the operation they wish to perform
            System.out.print(name + ", what type of operation do you wish to work (A for Addition, S for Subtraction, M for Multiplication, D for Division): ");
            String input = scanner.nextLine().trim();
            
            // Check if input is valid
            if (input.length() == 1) {
                operation = Character.toUpperCase(input.charAt(0));
                if (operation == 'A' || operation == 'S' || operation == 'M' || operation == 'D') {
                    break;
                }
            }
            System.out.println("Invalid operation. Please enter A, S, M, or D.");
        }
        
        // Assigning the full name of the operation
        switch (operation) {
            case 'A':
                longProbType = "Addition";
                break;
            case 'S':
                longProbType = "Subtraction";
                break;
            case 'M':
                longProbType = "Multiplication";
                break;
            case 'D':
                longProbType = "Division";
                break;
        }
        
        System.out.println("You selected: " + longProbType);
        
        // Get factor range with simple error handling
        int low = 0;

        while (true) {
            try {
                System.out.print("Enter the lowest factor value for your flashcard problems: ");
                low = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        int high = 0;

        while (true) {
            try {
                System.out.print("Enter the highest factor value for your flashcard problems: ");
                high = Integer.parseInt(scanner.nextLine());
                if (high >= low) {
                    break;
                } else {
                    System.out.println("Highest factor must be greater than or equal to lowest factor.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        // Get number of problems with simple error handling
        int numProblems = 0;
        while (true) {
            try {
                System.out.print("Enter the number of problems you wish to work: ");
                numProblems = Integer.parseInt(scanner.nextLine());
                if (numProblems > 0) {
                    break;
                } else {
                    System.out.println("Number of problems must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        // Welcome message and parameters confirmation
        System.out.println("\n\nHi " + name + ", and welcome to the 3312 FlashCard System!");
        System.out.println("You have chosen the operation " + longProbType + ".");
        System.out.println("The range of factors you have chosen is from " + low + " to " + high + ", inclusive.");
        System.out.println("You have chosen to work " + numProblems + " problems.");
        System.out.println("Press any character key and then Enter to begin.");
        scanner.nextLine();
        System.out.println("\n");
        
        // Start the flashcard problems
        int score = 0;
        String[] history = new String[numProblems];
        
        for (int i = 0; i < numProblems; i++) {
            int factor1 = random.nextInt(high - low + 1) + low;
            int factor2 = random.nextInt(high - low + 1) + low;
            int correctAnswer = 0;
            String problem = "";
            
            // Generate problem based on selected operation
            switch (operation) {
                // Addition
                case 'A': 
                    correctAnswer = factor1 + factor2;
                    problem = factor1 + " + " + factor2 + " = ";
                    break;
                
                // Subtraction
                case 'S':
                    // Ensure the result is non-negative
                    int larger = Math.max(factor1, factor2);
                    int smaller = Math.min(factor1, factor2);
                    correctAnswer = larger - smaller;
                    problem = larger + " - " + smaller + " = ";
                    break;
                
                // Multiplication
                case 'M': 
                    correctAnswer = factor1 * factor2;
                    problem = factor1 + " * " + factor2 + " = ";
                    break;
                
                // Division
                case 'D':
                    // Avoid division by zero
                    if (factor2 == 0) {
                        factor2 = 1;
                    }
                    // Make sure division results in a whole number
                    correctAnswer = factor2;
                    int dividend = factor1 * factor2;
                    problem = dividend + " / " + factor1 + " = ";
                    break;
            }
            
            // Get user answer with simple error handling
            int userAnswer = 0;
            while (true) {
                try {
                    System.out.print(problem);
                    userAnswer = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
            
            // Check if answer is correct
            if (userAnswer == correctAnswer) {
                System.out.println("Correct!");
                score++;
                history[i] = problem + userAnswer + ", Correct, correct answer is " + correctAnswer;
            } else {
                System.out.println("Incorrect! Correct answer is " + correctAnswer);
                history[i] = problem + userAnswer + ", Incorrect, correct answer is " + correctAnswer;
            }
            
            System.out.println("\n");
        }
        
        // Calculate score percentage
        double scorePct = ((double) score / numProblems) * 100;
        
        // Session summary
        System.out.println("\nSession Summary");
        System.out.println(numProblems + " problems, " + score + " correct");
        System.out.printf("Score is %.1f%%\n", scorePct);
        
        System.out.println("\nProblems:");
        for (String entry : history) {
            System.out.println(entry);
        }
        
        // Print outro
        System.out.println("\n\nThank you for using the 3312 FlashCard System, " + name + ".");
        System.out.println("Come back and play again real soon!");
        
        scanner.close();
    }
}