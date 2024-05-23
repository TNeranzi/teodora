package edu.scoalainformala.HW09Java8;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The main class for the filter by birth month application.
 * It reads a list of people from a file, filters them by birth month, and writes the filtered list to a new file.
 * File names are provided through user input.
 */

public class BirthdayFilterApp {

    //The beginning of the application.

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        ReadFile reader = new ReadFile();
        Filter filter = new Filter();
        WriteFile writer = new WriteFile();

        // Enter the file name with then people to filter
        System.out.println("Enter the name of the file you wish to filter (with extension): ");
        String inputFile = scanner.nextLine();

        // Validate the file name provided (to exist)
        while (!FileValidator.isValidFile(inputFile)) {
            System.out.println("Invalid file name. Please try again.");
            inputFile = scanner.nextLine();
        }

        // Load people from the input file
        List<Person> persons = reader.loadPersons(inputFile);

        System.out.println("Enter the month to filter by (1-12): ");
        int targetMonth = scanner.nextInt();

        // Validate the month given for filtering
        while (targetMonth < 1 || targetMonth > 12) {
            System.out.println("Invalid month. Please enter a number between 1 and 12.");
            targetMonth = scanner.nextInt();
        }

        // Filter the people by birth month
        List<Person> birthdayPersons = filter.filterByBirthMonth(persons, targetMonth);

        // Provide title for the file to be written
        System.out.println("Enter a name for your output file: ");
        String outputFile = FileValidator.getValidFileName(scanner);

        // Write the filtered persons to the output file
        try {
            writer.writePersons(birthdayPersons, outputFile);
            System.out.println("The list of people born in month " + targetMonth + " has been saved to " + outputFile);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}