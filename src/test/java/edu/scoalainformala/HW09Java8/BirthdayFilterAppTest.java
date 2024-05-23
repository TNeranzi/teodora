package edu.scoalainformala.HW09Java8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BirthdayFilterAppTest {

    private List<Person> persons;
    Filter filter = new Filter();

    // Initializes the persons list before each test case.
    @BeforeEach
    public void setUp() {
        persons = Arrays.asList(
                new Person("John", "Doe", LocalDate.of(1990, 12, 15)),
                new Person("Jane", "Smith", LocalDate.of(1985, 12, 20)),
                new Person("Alice", "Johnson", LocalDate.of(1995, 11, 5)),
                new Person("Bob", "Williams", LocalDate.of(1980, 12, 1))
        );
    }

    // Tests the filterByBirthMonth method with a list of persons and a specific birth month.
    @Test
    public void testFilterByBirthMonth() {
        List<Person> birthdayPersons = filter.filterByBirthMonth(persons, 12);

        assertEquals(3, birthdayPersons.size());
        assertEquals("Bob", birthdayPersons.get(0).getFirstName());
        assertEquals("Jane", birthdayPersons.get(1).getFirstName());
        assertEquals("John", birthdayPersons.get(2).getFirstName());
    }

    // Tests the filterByBirthMonth method with an empty list and a specific birth month.
    @Test
    public void testFilterByBirthMonthWithEmptyList() {
        List<Person> emptyList = new ArrayList<>();
        List<Person> birthdayPersons = filter.filterByBirthMonth(emptyList, 12);

        assertEquals(0, birthdayPersons.size());
    }

    // Tests the filterByBirthMonth method with a list of persons and a specific birth month that brings no matches.
    @Test
    public void testFilterByBirthMonthWithNoMatchingPersons() {
        List<Person> noMatchingPersons = Arrays.asList(
                new Person("Jane", "Smith", LocalDate.of(1985, 8, 20)),
                new Person("Alice", "Johnson", LocalDate.of(1995, 11, 5))
        );
        List<Person> birthdayPersons = filter.filterByBirthMonth(noMatchingPersons, 12);

        assertEquals(0, birthdayPersons.size());
    }
}

