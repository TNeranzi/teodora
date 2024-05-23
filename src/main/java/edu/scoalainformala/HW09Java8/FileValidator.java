package edu.scoalainformala.HW09Java8;

import java.io.File;
import java.util.Scanner;


// This class provides methods to validate and get valid file names.

public class FileValidator {

    // Checks if a given file name is valid (exists).
    public static boolean isValidFile(String fileName) {
        if (fileName == null) {
            return false;
        }
        File file = new File(fileName);
        return file.exists() && !file.isDirectory();
    }

    // Checks the validity of the file name from the user input.

    public static String getValidFileName(Scanner scanner) {
        String outputFile = scanner.nextLine();

        while (true) {
            if (outputFile.isEmpty()) {
                outputFile = scanner.nextLine();

                if (!outputFile.isEmpty()) {
                    break;
                } else {
                    System.out.println("Invalid file name. Please try again.");
                }
            }
        }
        return outputFile;
    }
}
