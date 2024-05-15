package edu.scoalainformala.HW08Exceptions;

import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Logger;

public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String id;
    public static final Logger logger = Logger.getLogger(Student.class.getName());

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String gender, String id) throws Exception {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateDateOfBirth(dateOfBirth);
        validateGender(gender);
        validateId(id);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void validateFirstName(String firstName) throws Exception {
        if (firstName == null || firstName.isEmpty()) {
            logger.severe("First name cannot be empty.");
            throw new Exception("First name cannot be empty.");
        }
    }

    private void validateLastName(String lastName) throws Exception {
        if (lastName == null || lastName.isEmpty()) {
            logger.severe("Last name cannot be empty.");
            throw new Exception("Last name cannot be empty.");
        }
    }

    private void validateDateOfBirth(LocalDate dateOfBirth) throws Exception {
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = LocalDate.of(1900, 1, 1);

        if (dateOfBirth == null || dateOfBirth.isBefore(minDate) || dateOfBirth.isAfter(currentDate)) {
            logger.severe("Date of birth must be between 1900 and current year.");
            throw new Exception("Date of birth must be between 1900 and current year.");
        }

        int age = Period.between(dateOfBirth, currentDate).getYears();
        if (age < 18) {
            logger.severe("Student must be at least 18 years old.");
            throw new Exception("Student must be at least 18 years old.");
        }
    }

    private void validateGender(String gender) throws Exception {
        if (gender == null || (!gender.equalsIgnoreCase("male") &&!gender.equalsIgnoreCase("female") &&
                !gender.equalsIgnoreCase("M") &&!gender.equalsIgnoreCase("F"))) {
            logger.severe("Gender must be either 'ale', 'female', 'M', or 'F'.");
            throw new Exception("Gender must be either 'ale', 'female', 'M', or 'F'.");
        }
    }

    private void validateId(String id) throws Exception {
        // Implement CNP validation logic here
        if (id == null || id.isEmpty()) {
            logger.severe("ID cannot be empty.");
            throw new Exception("ID cannot be empty.");
        }
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

}
