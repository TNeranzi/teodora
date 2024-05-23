package edu.scoalainformala.HW09Java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is responsible for reading a file and creating a list of Person objects.
 * Each line in the file should contain a comma-separated list of first name, last name, and date of birth.
 * The date of birth should be in the format "yyyy-MM-dd".
 */
public class ReadFile {

    // Logger for logging warnings and errors.
    private static final Logger logger = Logger.getLogger(ReadFile.class.getName());

    // Reads a file and creates a list of Person objects.
    public List<Person> loadPersons(String fileName) throws IOException {

        // Create an empty list to store the Person objects.
        List<Person> persons = new ArrayList<>();

        // Use a try-with-resources statement to automatically close the BufferedReader.
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {

                // Split the line into an array of values.
                String[] values = line.split(",");

                // Check if the line has at least 3 values.
                if (values.length >= 3) {

                    // Get the first name, last name, and date of birth from the values.
                    String firstName = values[0];
                    String lastName = values[1];

                    try {

                        // Parse the date of birth using the specified format.
                        LocalDate dateOfBirth = LocalDate.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Create a new Person object and add it to the list.
                        persons.add(new Person(firstName, lastName, dateOfBirth));
                    } catch (DateTimeParseException e) {

                        // If the date of birth is in an invalid format, throw an IOException.
                        throw new IOException("Invalid date format for line: " + line, e);
                    }
                } else {

                    // If the line does not have enough values, write a warning log.
                    logger.warning("Invalid input format: " + line);
                }
            }
        }

        // Return the list of Person objects.
        return persons;
    }
}