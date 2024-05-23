package edu.scoalainformala.HW09Java8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile {

    /**
     * This class is responsible for writing the filtered file.
     * The file contains the first and last name of the people with the specified month of birth.
     * The listed people are sorted alphabetically by their first name.
     **/

    public void writePersons(List<Person> persons, String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Invalid file name.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Person person : persons) {
                bw.write(person.getFirstName() + " " + person.getLastName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}